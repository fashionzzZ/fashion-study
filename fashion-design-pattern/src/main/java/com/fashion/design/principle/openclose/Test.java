package com.fashion.design.principle.openclose;

/**
 * 开闭原则-测试类
 *
 * @author Wuxf
 * @since 2020-05-08 22:27:35
 **/
public class Test {
    public static void main(String[] args) {
        ICourse course = new JavaDiscountCourse(10, "Java课程", 368d);
        JavaDiscountCourse javaCourse = (JavaDiscountCourse) course;
        System.out.println("课程ID：" + javaCourse.getId()
                + " 课程名称：" + javaCourse.getName()
                + " 课程原价：" + javaCourse.getOriginPrice()
                + " 打折价格：" + javaCourse.getPrice());
    }
}
