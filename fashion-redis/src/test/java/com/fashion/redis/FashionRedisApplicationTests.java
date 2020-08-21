package com.fashion.redis;

import com.fashion.redis.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FashionRedisApplicationTests {

    @Autowired
    ProductService productService;

    @Test
    void testCreateOrder() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                productService.updateProductInventory("10001", 1);
            }).start();
        }
    }

}
