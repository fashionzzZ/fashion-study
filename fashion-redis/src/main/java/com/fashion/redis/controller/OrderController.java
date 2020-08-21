package com.fashion.redis.controller;

import com.fashion.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
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
    ProductService productService;

    @RequestMapping("/order")
    public boolean createOrder() {
        // 模拟并发请求
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                runnable -> new Thread(runnable, "order-create"));

        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(() -> {
                productService.updateProductInventory("1", 1);
            });
        }
        return true;
    }
}
