package com.fashion.jvm;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试雪花算法
 *
 * @author Wuxf
 * @since 2020-05-22 16:42:18
 **/
public class TestSnowFlake {

    private static final SnowFlake SNOW_FLAKE = new SnowFlake(1, 1);

    private static Set<Long> set = new HashSet<>();

    private static Set<Long> syncSet = Collections.synchronizedSet(set);

    private static void getNextId() {
        syncSet.add(SNOW_FLAKE.nextId());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(TestSnowFlake::getNextId);
        }
        Thread.sleep(3000);
        System.out.println(syncSet.size());
        for (Long aLong : syncSet) {
            System.out.println(aLong);
        }
    }

}
