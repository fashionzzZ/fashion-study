package com.fashion.design.principle.singleresponsibility;

/**
 * 单一职责-测试类
 *
 * @author Wuxf
 * @since 2020-05-10 21:48:45
 **/
public class Test {
    public static void main(String[] args) {
        Bird bird = new Bird();
        bird.mainMoveModel("大雁");
        bird.mainMoveModel("鸵鸟");

        FlyBird flyBird = new FlyBird();
        flyBird.mainMoveModel("大雁");

        WalkBird walkBird = new WalkBird();
        walkBird.mainMoveModel("鸵鸟");
    }
}
