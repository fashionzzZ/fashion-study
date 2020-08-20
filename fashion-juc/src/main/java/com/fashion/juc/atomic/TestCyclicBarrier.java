package com.fashion.juc.atomic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试CyclicBarrier - 循环栅栏
 *
 * @author Wuxf
 * @since 2020-05-12 13:43:39
 **/
public class TestCyclicBarrier {
    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(20);
        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人，发车"));
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    barrier.await(); // barrier的值-1，直到barrier的值为0，才往下执行，否则阻塞在此。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
