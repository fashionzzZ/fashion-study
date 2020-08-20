package com.fashion.spring;

import java.util.StringJoiner;

/**
 * 用户类
 *
 * @author Wuxf
 * @since 2020-07-23
 **/
public class User {

    private String name;

    public User() {
        System.out.println("User对象被创建了");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
