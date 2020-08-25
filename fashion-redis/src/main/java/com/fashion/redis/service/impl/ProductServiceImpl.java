package com.fashion.redis.service.impl;

import com.fashion.redis.service.ProductService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * 订单接口实现
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private static AtomicInteger index = new AtomicInteger();
    private static AtomicInteger count = new AtomicInteger();

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean updateProductInventory(String productId, Integer quantity) {
        boolean res = false;
        int bucket = getBucket();
        LOGGER.info(Thread.currentThread().getName() + " count:" + count.incrementAndGet());
        String productKey = productId + "_" + bucket;
        String lockKey = "LOCK_" + productKey;
        RLock lock = redissonClient.getLock(lockKey);
        // 1.获取锁
        lock.lock();
        try {
            // 2.查询商品信息
            String value = stringRedisTemplate.opsForValue().get(productKey);
            long inventory = 0;
            if (!StringUtils.isEmpty(value) && Integer.parseInt(value) > 0) {
                inventory = Long.parseLong(value);
            }

            // 3.判断商品库存是否满足本次购买
            if (inventory > 0 && inventory >= quantity) {
                // 4.修改商品库存
                stringRedisTemplate.opsForValue().set(productKey, String.valueOf(inventory - quantity));
                res = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(Thread.currentThread().getName() + " 获取锁失败");
        } finally {
            // 最后需要解锁操作
            lock.unlock();
        }
        return res;
    }

    @Override
    public boolean setRedisProductInventory() {
        for (int i = 0; i < 10; i++) {
            stringRedisTemplate.opsForValue().set("1_" + i, "1000");
        }
        return true;
    }

    private int getBucket() {
        return index.incrementAndGet() % 10;
    }
}
