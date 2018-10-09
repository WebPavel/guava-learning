package com.ava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableTest {

    @Test
    public void testJDKImmutable() {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(list);
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);
        String temp = unmodifiableList.get(1);
        System.out.println("unmodifiableList"+temp);
    }
}
