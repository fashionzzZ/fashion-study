package com.fashion.design.principle.demeter.right;

/**
 * Boss
 *
 * @author Wuxf
 * @since 2020-05-10 22:35:50
 **/
public class Boss {
    public void commandCheckNumber(TeamLeader teamLeader) {
        teamLeader.checkNumberOfPerson();
    }
}
