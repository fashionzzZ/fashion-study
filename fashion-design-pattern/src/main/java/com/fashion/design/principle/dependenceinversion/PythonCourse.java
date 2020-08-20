package com.fashion.design.principle.dependenceinversion;

/**
 * 依赖倒置原则-Python课程实现类
 *
 * @author Wuxf
 * @since 2020-05-08 22:57:33
 **/
public class PythonCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("Fashion正在学习Python课程");
    }
}
