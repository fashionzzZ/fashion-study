package com.fashion.juc;

import java.util.Random;

/**
 * 测试类
 *
 * @author Wuxf
 * @date 2020/08/14
 **/
public class Test {
    public static void main(String[] args) {

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(100));
        }
    }
}
