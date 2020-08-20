package com.fashion.design.principle.openclose;

/**
 * Java课程打折类
 *
 * @author Wuxf
 * @since 2020-05-08 22:26:03
 **/
public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginPrice() {
        return super.getPrice();
    }

    @Override
    public Double getPrice() {
        return super.getPrice() * 0.8;
    }
}
