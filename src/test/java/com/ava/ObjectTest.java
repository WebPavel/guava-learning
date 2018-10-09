package com.ava;

import com.google.common.base.Objects;
import org.junit.Test;

public class ObjectTest {

    @Test
    public void testEqual(){
        System.out.println(Objects.equal("null","null"));
        System.out.println(Objects.equal(null,null));
        System.out.println(Objects.equal("null",null));
    }

    @Test
    public void testEqualPerson() {
        System.out.println(Objects.equal(new Person("lb",23),new Person("lb",23)));
        Person person = new Person("lier",23);
        System.out.println(Objects.equal(person,person));
    }

    @Test
    public void testHashcode() {
        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a","b"));
        System.out.println(Objects.hashCode("b","a"));
        System.out.println(Objects.hashCode("a","b","c"));

        Person person = new Person("lisy",22);
        System.out.println(Objects.hashCode(person));
        System.out.println(Objects.hashCode(person));
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
