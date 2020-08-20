package com.fashion.design.principle.interfacesegregation.wrong;

/**
 * 狗
 *
 * @author Wuxf
 * @since 2020-05-10 22:14:01
 **/
public class Dog implements IAnimalAction {
    @Override
    public void eat() {

    }

    @Override
    public void fly() { // 狗不会飞，所以这里是空实现

    }

    @Override
    public void swim() {

    }
}
