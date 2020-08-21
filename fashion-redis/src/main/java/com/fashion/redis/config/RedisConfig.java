package com.fashion.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置
 *
 * @author Wuxf
 * @date 2020/08/20
 **/
@Configuration
public class RedisConfig {

    /**
     * 单个redisson
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        config.setLockWatchdogTimeout(3000L);
        return Redisson.create(config);
    }
}
