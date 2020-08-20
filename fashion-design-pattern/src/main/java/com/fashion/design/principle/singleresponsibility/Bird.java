package com.fashion.design.principle.singleresponsibility;

/**
 * 鸟
 *
 * @author Wuxf
 * @since 2020-05-10 21:46:40
 **/
public class Bird {
    public void mainMoveModel(String birdName) {
        if ("鸵鸟".equals(birdName)) {
            System.out.println(birdName + "用脚走");
        } else {
            System.out.println(birdName + "用翅膀飞");
        }
    }
}
