package com.fashion.design.principle.openclose;

/**
 * Java课程类
 *
 * @author Wuxf
 * @since 2020-05-08 22:23:06
 **/
public class JavaCourse implements ICourse {
    Integer id;
    String name;
    Double price;

    public JavaCourse(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
