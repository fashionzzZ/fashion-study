package com.fashion.juc.atomic;

import java.util.concurrent.Exchanger;

/**
 * 测试Exchanger - 线程交换数据
 *
 * @author Wuxf
 * @since 2020-05-12 13:53:20
 **/
public class TestExchanger {
    public static Exchanger<String> exchanger = new Exchanger<>();

    // 将t1线程的变量s->"T1"与t2线程的变量s->"T2"进行交换
    // 交换之后，t1线程的s为"T2"，t2线程的s为"T1"
    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }
}
