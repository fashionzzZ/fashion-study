package com.fashion.design.principle.dependenceinversion;

/**
 * 依赖倒置原则-Java课程实现类
 *
 * @author Wuxf
 * @since 2020-05-08 22:56:12
 **/
public class JavaCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("Fashion正在学习Java课程");
    }
}
