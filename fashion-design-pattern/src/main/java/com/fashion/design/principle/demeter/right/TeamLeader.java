package com.fashion.design.principle.demeter.right;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wuxf
 * @since 2020-05-10 22:37:20
 **/
public class TeamLeader {
    public void checkNumberOfPerson() {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            personList.add(new Person());
        }
        System.out.println("今天来上班的人数是：" + personList.size());
    }
}
