package com.fashion.netty.nio.reactor;

/**
 * @author Wuxf
 * @date 2020/08/21
 **/
public class Main {
    public static void main(String[] args) {
        SelectorThreadGroup group = new SelectorThreadGroup(1);
        group.bind(9999);

    }
}
