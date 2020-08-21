package com.fashion.redis.service.impl;

import com.fashion.redis.dao.ProductMapper;
import com.fashion.redis.entity.Product;
import com.fashion.redis.service.OrderService;
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
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    ProductMapper productMapper;

    @Override
    public boolean createOrder(String productId, Integer quantity) {
        boolean res = false;
        String lockKey = "PRODUCT_" + productId;
        RLock lock = redissonClient.getLock(lockKey.intern());
        lock.lock();
        try {
            LOGGER.info(Thread.currentThread().getName() + " 获取锁成功");
            Product product = productMapper.selectById(Long.valueOf(productId));
            Long inventory = product.getInventory();
            if (inventory > 0 && inventory >= quantity) {
                int rows = productMapper.updateInventoryById(Long.valueOf(productId), inventory - quantity);
                if (rows > 0) {
                    res = true;
                }
            }
            LOGGER.info(Thread.currentThread().getName() + " 业务执行完毕");
        } catch (Exception e) {
            throw new RuntimeException(Thread.currentThread().getName() + " 获取锁失败");
        } finally {
            lock.unlock();
            LOGGER.info(Thread.currentThread().getName() + " 释放锁成功");
        }
        return res;
    }
}
