package com.ava;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PreconditionTest {

    @Test
    public void testPrecondition() {
        try {
            getPersonByPrecondition(23,null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            getPersonByPrecondition(23,"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            getPersonByPrecondition(-9,"xiaoer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                checkState(integerList,8);
                integerList.add(i);
                System.out.println("成功添加"+i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            checkPositionIndex(integerList,3);
            System.out.println("checkPositionIndex");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            checkPositionIndexes(integerList,3,7);
            System.out.println("checkPositionIndexes");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            checkElementIndex(integerList,23);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkElementIndex(List<Integer> integerList, int index) {
        Preconditions.checkElementIndex(index,integerList.size(),"index: "+index+" 不在list范围内");
    }

    private void checkPositionIndexes(List<Integer> integerList, int start, int end) {
        Preconditions.checkPositionIndexes(start,end,integerList.size());
    }

    private void checkPositionIndex(List<Integer> integerList, int index) {
        //checkPositionIndex(index,size)检查一个index是否在一个size的list，string，array的范围内
        Preconditions.checkPositionIndex(index,integerList.size(),"index "+index+"不在list中"+integerList);
    }

    private void checkState(List<Integer> integerList, int index) {
        //检查对象的状态如Iterator.next()是否在remove之前被调用
        //checkState(boolean)
        Preconditions.checkState(integerList.size()<index,"integerList size不能大于"+index);
    }

    public static  void getPersonByPrecondition(int age,String name) {
        //checkNotNull(T)检查value不为null直接返回value
        Preconditions.checkNotNull(name,"name不允许为空");
        Preconditions.checkArgument(name.length()>0,"name不允许为\'\'");
        Preconditions.checkArgument(age>0,"不合法的age");
        System.out.println(age+":"+name);
    }
}
