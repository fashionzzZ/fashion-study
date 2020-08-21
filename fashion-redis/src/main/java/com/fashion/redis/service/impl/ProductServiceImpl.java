package com.fashion.redis.service.impl;

import com.fashion.redis.dao.ProductMapper;
import com.fashion.redis.entity.Product;
import com.fashion.redis.service.ProductService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单接口实现
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    ProductMapper productMapper;

    @Override
    public boolean updateProductInventory(String productId, Integer quantity) {
        boolean res = false;
        String lockKey = "PRODUCT_" + productId;
        RLock lock = redissonClient.getLock(lockKey.intern());
        // 1.获取锁
        lock.lock();
        try {
            LOGGER.info(Thread.currentThread().getName() + " 获取锁成功");
            // 2.查询商品信息
            Product product = productMapper.selectById(Long.valueOf(productId));
            // 3.判断商品库存是否满足本次购买
            Long inventory = product.getInventory();
            if (inventory > 0 && inventory >= quantity) {
                // 4.修改商品库存
                int rows = productMapper.updateInventoryById(Long.valueOf(productId), inventory - quantity);
                if (rows > 0) {
                    res = true;
                }
            }
            LOGGER.info(Thread.currentThread().getName() + " 业务执行完毕");
        } catch (Exception e) {
            throw new RuntimeException(Thread.currentThread().getName() + " 获取锁失败");
        } finally {
            // 最后需要解锁操作
            lock.unlock();
            LOGGER.info(Thread.currentThread().getName() + " 释放锁成功");
        }
        return res;
    }
}
