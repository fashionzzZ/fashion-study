package com.fashion.design.principle.dependenceinversion;

/**
 * 依赖倒置原则-测试类
 *
 * @author Wuxf
 * @since 2020-05-08 23:01:31
 **/
public class Test {
//    /**
//     * 接口注入
//     */
//    public static void main(String[] args) {
//        Fashion fashion = new Fashion();
//        fashion.study(new JavaCourse());
//        fashion.study(new PythonCourse());
//    }

//    /**
//     * 构造器注入
//     */
//    public static void main(String[] args) {
//        Fashion fashion = new Fashion(new JavaCourse());
//        fashion.study();
//    }

    /**
     * Setter注入
     */
    public static void main(String[] args) {
        Fashion fashion = new Fashion();
        fashion.setCourse(new JavaCourse());
        fashion.study();

        fashion.setCourse(new PythonCourse());
        fashion.study();
    }
}
