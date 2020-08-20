package com.fashion.juc.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 查看HashMap源码
 *
 * @author Wuxf
 * @since 2020-05-28 23:19:36
 **/
public class HashMapCode {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");
        System.out.println(map.get("hello"));
    }
}
