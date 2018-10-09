package com.ava;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.Comparator;

public class ObjectsTest {

    @Test
    public void testStudent() {
        Student student = new Student("baidu",98,59);
        Student student1 = new Student("alibaba",98,89);
        Student student2 = new Student("tencent",88,70);
        Student student3 = new Student("baidu",98,59);
        System.out.println("==========equals============");
        System.out.println(student.equals(student1));
        System.out.println(student.equals(student2));
        System.out.println(student.equals(student3));
        System.out.println("==========hashcode============");
        System.out.println(student.hashCode());
        System.out.println(student1.hashCode());
        System.out.println(student2.hashCode());
        System.out.println(student3.hashCode());
        System.out.println("==========toString============");
        System.out.println(student.toString());
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println(student3.toString());
        System.out.println("==========compareTo============");
        System.out.println(student.compareTo(student1));
        System.out.println(student.compareTo(student2));
        System.out.println(student2.compareTo(student));
    }
}

class Student implements Comparable<Student> {

    private String name;
    private int age;
    private int score;

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name,age);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student stu = (Student) obj;
            return Objects.equal(name,stu.name)
                    && Objects.equal(age,stu.age)
                    && Objects.equal(score,stu.score);
        }
        return false;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(name)
                .addValue(age)
                .addValue(score)
                .toString();
    }

    @Override
    public int compareTo(Student other) {
        return ComparisonChain.start()
                .compare(name,other.name)
                .compare(age,other.age)
                .compare(score,other.score, Ordering.<Integer>natural().nullsLast())
                .result();
    }
}

//class StudentComparator implements Comparator<Student> {
//
//    @Override
//    public int compare(Student o1, Student o2) {
//        return ComparisonChain.start()
//                .compare(o1.getName(),o2.getName())
//                .compare(o1.getAge(),o2.getAge())
//                .compare(o1.getScore(),o2.getScore())
//                .result();
//    }
//}