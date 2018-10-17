package com.hupper.rpc.test.client;

import java.util.List;

/**
 * Created by lvhongpeng on 2016-03-10.
 */
public interface PersonService {
    List<Person> GetTestPerson(String name, int num);
}
