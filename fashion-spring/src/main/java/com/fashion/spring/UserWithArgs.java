package com.fashion.spring;

import java.util.StringJoiner;

/**
 * 有参构造器的用户类
 *
 * @author Wuxf
 * @since 2020-07-23
 **/
public class UserWithArgs {

    private String name;

    public UserWithArgs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserWithArgs.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
