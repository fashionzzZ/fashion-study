package com.fashion.design.principle.demeter.wrong;

/**
 * 测试类
 *
 * @author Wuxf
 * @since 2020-05-10 22:41:17
 **/
public class Test {
    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        boss.commandCheckNumber(teamLeader);
    }
}
