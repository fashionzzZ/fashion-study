package com.fashion.juc.atomic;

import java.util.concurrent.Semaphore;

/**
 * 测试Semaphore - 信号量
 *
 * @author Wuxf
 * @since 2020-05-12 13:51:19
 **/
public class TestSemaphore {
    public static void main(String[] args) {
        // 只允许2个线程同时执行,第二个参数为true表示使用公平锁（使用AQS维护的队列），默认是非公平锁
        Semaphore s = new Semaphore(2, true);

        new Thread(()->{
            try {
                s.acquire(); // 每次执行acquire()方法s的值-1，如果s的值为0，当前线程就不能往下执行了，除非其它线程调用release()方法。

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release(); // s的值+1
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
