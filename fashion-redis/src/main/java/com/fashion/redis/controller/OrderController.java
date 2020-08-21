package com.fashion.redis.controller;

import com.fashion.redis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 订单服务
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public boolean createOrder() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,
                10,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable runnable) {
                        return new Thread(runnable, "order-create");
                    }
                });

        for (int i = 0; i < 20; i++) {
            poolExecutor.execute(() -> {
                orderService.createOrder("1", 3);
            });
        }
        return true;
    }
}
