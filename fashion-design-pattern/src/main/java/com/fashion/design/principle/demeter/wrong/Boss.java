package com.fashion.design.principle.demeter.wrong;

import java.util.ArrayList;
import java.util.List;

/**
 * Boss
 *
 * @author Wuxf
 * @since 2020-05-10 22:35:50
 **/
public class Boss {
    public void commandCheckNumber(TeamLeader teamLeader) {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            personList.add(new Person());
        }
        teamLeader.checkNumberOfPerson(personList);
    }
}
