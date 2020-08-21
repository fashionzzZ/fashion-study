package com.fashion.redis.service;

/**
 * 订单接口
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
public interface OrderService {
    boolean createOrder(String productId, Integer quantity);
}
