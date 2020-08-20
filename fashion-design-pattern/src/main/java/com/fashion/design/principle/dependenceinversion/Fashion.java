package com.fashion.design.principle.dependenceinversion;

/**
 * 依赖倒置原则-用户类
 *
 * @author Wuxf
 * @since 2020-05-08 22:58:56
 **/
public class Fashion {
    ICourse course;

    public void setCourse(ICourse course) {
        this.course = course;
    }

    public void study() {
        this.course.studyCourse();
    }
}
