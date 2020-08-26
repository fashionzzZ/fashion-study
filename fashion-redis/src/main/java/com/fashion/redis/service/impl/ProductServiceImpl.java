package com.fashion.redis.service.impl;

import com.fashion.redis.service.ProductService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分布式锁-分段锁实现
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private static final int INVENTORY = 10000;
    private static final int BUCKET_COUNT = 50;
    private static AtomicInteger INDEX = new AtomicInteger();

    @Value("${server.port}")
    private String port;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean updateProductInventory(String productId, Integer quantity) {
        boolean res = false;
        int bucket = getBucket();
        String productKey = productId + "_" + bucket;
        String lockKey = "LOCK_" + productKey;
        RLock lock = redissonClient.getLock(lockKey);
        // 1.获取锁
        lock.lock();
        try {
            LOGGER.info("获得锁成功: " + lockKey);
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
            } else {
                LOGGER.warn(Thread.currentThread().getName() + " 库存不足: " + productKey);
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
    public boolean setRedisProductInventory(String productId) {
        for (int i = 0; i < (BUCKET_COUNT * 2); i++) {
            stringRedisTemplate.opsForValue().set(productId + "_" + i, String.valueOf(INVENTORY / (BUCKET_COUNT * 2)));
        }
        return true;
    }

    private int getBucket() {
        if ("8002".equals(port)) {
            return 50 + INDEX.incrementAndGet() % BUCKET_COUNT;
        }
        return INDEX.incrementAndGet() % BUCKET_COUNT;
    }
}
