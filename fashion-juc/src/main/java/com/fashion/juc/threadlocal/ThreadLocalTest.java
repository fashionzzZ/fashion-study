package com.fashion.juc.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * 测试ThreadLocal
 *
 * @author Wuxf
 * @since 2020-07-23
 **/
public class ThreadLocalTest {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            tl.set(new Person()); // 往tl中set一个Person对象
            System.out.println(Thread.currentThread().getName() + " 设置tl的值");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 拿到的值为：" + tl.get()); // 从tl中get值，拿不到
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            tl.set(new Person()); // 往tl中set一个Person对象
            System.out.println(Thread.currentThread().getName() + " 设置tl的值");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 拿到的值为：" + tl.get());
            tl.remove();
            System.out.println(Thread.currentThread().getName() + " 删除后拿到的值为：" + tl.get());
        }).start();
    }

    static class Person {
        String name = "zhangsan";
    }
}
