package com.fashion.juc;

/**
 * @author Wuxf
 * @date 2020/08/19
 **/
public class Singleton {
    public static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
