package com.fashion.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类
 * <p>
 * 学习Spring IOC，测试Spring以配置文件的方式注入bean
 * </p>
 *
 * @author Wuxf
 * @since 2020-07-23
 **/
public class MyTest {
    public static void main(String[] args) {
        // 获取Spring上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 通过Spring上下文获取bean对象
        // 1.使用无参构造器创建
        User user = (User) context.getBean("user");
        System.out.println(user.toString());

        // 2.使用有参构造器创建
        UserWithArgs userWithArgs = (UserWithArgs) context.getBean("userWithArgs");
        System.out.println(userWithArgs.toString());
    }
}
