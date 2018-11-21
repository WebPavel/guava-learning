package com.ava;

import com.google.common.base.Objects;
import com.google.common.primitives.Ints;
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

    @Test
    public void testToString() {
        System.out.println(Objects.toStringHelper(this).add("x",1).toString());
        System.out.println(Objects.toStringHelper(Person.class).add("x",1).toString());

        Person person = new Person("lily",18);
        String result = Objects.toStringHelper(Person.class)
                .add("name",person.getName())
                .add("age",person.getAge()).toString();
        System.out.println(result);
    }

    @Test
    public void testCompare() {
        Person person = new Person("lucy",23);
        Person person1 = new Person("kangkang",23);
        Person person2 = new Person("lucy",19);
        Person person3 = new Person("linda",20);
        System.out.println(person.compareTo(person1));
        System.out.println(person.compareTo(person2));
        System.out.println(person.compareTo(person3));
        System.out.println(person2.compareTo(person3));
        int numI = 'i';
        int numU = 'u';
        System.out.println(numU-numI);
        System.out.println(Ints.compare(9,10));
    }
}

class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        int cmpName = name.compareTo(other.getName());
        if (cmpName != 0) {
            return cmpName;
        }
        if (age > other.getAge()) {
            return 1;
        }
        if (age < other.getAge()) {
            return  -1;
        }
        return 0;
    }
}
