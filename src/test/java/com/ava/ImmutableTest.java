package com.ava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> unmodifiableList1 = Collections.unmodifiableList(Arrays.asList("a","b","c"));
        System.out.println(unmodifiableList1);

        String temp = unmodifiableList.get(0);
        System.out.println("unmodifiableList[0] : " + temp);

        list.add("ddd");
        System.out.println("add after list :" + list);
        System.out.println("add after unmodifiable list :" + unmodifiableList);

        try {
            unmodifiableList1.add("d");
            System.out.println("add an element after unmodifiable list :" + unmodifiableList1);
        } catch (Exception e) {
            System.out.println("cause error,exit");
        }

        unmodifiableList.add("eee");
        System.out.println("now list :" + unmodifiableList);
    }

    @Test
    public void  testGuavaImmutable() {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(list);

        ImmutableList<String> immutableList = ImmutableList.copyOf(list);
        System.out.println(immutableList);

        ImmutableSet<String> immutableSet = ImmutableSet.of("linda","university","college");
        System.out.println(immutableSet);

        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.of("a","b","c","a","d","b","aa");
        System.out.println(immutableSortedSet);

        list.add("ddd");
        System.out.println("list after add one item: " + list);
        System.out.println("list after add one item of ImmutableList" + immutableList);

        ImmutableSet<Color> colorImmutableSet = ImmutableSet.<Color>builder()
                .add(new Color(0,255,255))
                .add(new Color(0,191,255))
                .build();
        System.out.println(colorImmutableSet);
    }

    /**
     * 创建immutable集合的三种方法
     * 1.使用copyOf方法
     * 2.使用of方法实例化
     * 3.使用Builder类
     */

    @Test
    public void testCopyOf() {
        ImmutableSet<String> immutableSet = ImmutableSet.of("pig","cat","mouse","horse");
        System.out.println(immutableSet);
        ImmutableList<String> immutableList = ImmutableList.copyOf(immutableSet);
        System.out.println(immutableList);
        ImmutableSortedSet immutableSortedSet = ImmutableSortedSet.copyOf(immutableSet);
        System.out.println(immutableSortedSet);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "x");
        }
        System.out.println(list);
        ImmutableList<String> immutableListOfSubList = ImmutableList.copyOf(list.subList(2,18));
        System.out.println(immutableListOfSubList);
        System.out.println(immutableListOfSubList.size());
        ImmutableSet<String> immutableSetOfSubList = ImmutableSet.copyOf(immutableListOfSubList.subList(2, 5));
        System.out.println(immutableSetOfSubList);
    }

    @Test
    public void testAsList() {
        ImmutableList<String> immutableList = ImmutableList.of("aaa","bbb","ccc","ddd");
        System.out.println(immutableList);
        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.copyOf(immutableList);
        System.out.println(immutableSortedSet);
        System.out.println(immutableSortedSet.asList());
        System.out.println(immutableList.asList().get(2));
    }
}
