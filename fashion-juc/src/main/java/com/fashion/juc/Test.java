package com.fashion.juc;

/**
 * 测试类
 *
 * @author Wuxf
 * @date 2020/08/14
 **/
public class Test {
    public static void main(String[] args) {
        String s1 = "programming";
        String s2 = "ming";
        String s3 = "program" + s2;
        String s4 = "program" + "ming";
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
    }
}
