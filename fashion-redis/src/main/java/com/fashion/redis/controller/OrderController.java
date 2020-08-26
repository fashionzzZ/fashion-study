package com.fashion.redis.controller;

import com.fashion.redis.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createOrder() {
        boolean res = productService.updateProductInventory("1", 1);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK).body("减库存成功");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("库存不足");
    }

    @RequestMapping("/set")
    public ResponseEntity setInventory() {
        boolean res = productService.setRedisProductInventory("1");
        if (res) {
            return ResponseEntity.status(HttpStatus.OK).body("设置库存成功");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("设置库存失败");
    }
}
