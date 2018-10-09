package com.ava;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.Set;


public class OptionalTest {
    /**
     * 静态方法
     */
    @Test
    public void testOptional() {
        Optional<Integer> optional = Optional.of(6);//获取一个optional对象，optional对象内部包含一个非null的T数据类型实例，当T=null时会立即报错
        Optional<Integer> absentOpt = Optional.absent();//获取一个optional对象，内部包含空值
        Optional<Integer> nullableOpt = Optional.fromNullable(null);//将一个T的实例对象转换为optional对象，T的实例可以不为空，T的实例也可以为空（Optional.fromNullable(null)与Optional.absent()等价）
        Optional<Integer> noNullableOpt = Optional.fromNullable(6);
        if (optional.isPresent()) {
            System.out.println(optional.isPresent());
            System.out.println("optional的值为"+optional.get());
        }
        if(absentOpt.isPresent()) {
            System.out.println(absentOpt.isPresent());
        }
        if (nullableOpt.isPresent()) {
            System.out.println(nullableOpt.isPresent());
        }
        if (noNullableOpt.isPresent()) {
            System.out.println(noNullableOpt.isPresent());
        }
    }

    /**
     * 实例方法
     */
    @Test
    public void testOptionalMethod() {
        Optional<Long> value = method();
        if (value.isPresent()) {
            System.out.println(value.get());
        } else {
            //T or(T)若optional中存在该实例则返回该实例否则返回输入的T的实例作为默认值
            System.out.println(value.or(-6L));
        }
        //orNull()返回optional包含的非空T实例，如果为空值则返回null,逆操作为fromNullable()
        System.out.println(value.orNull());

        System.out.println("---------i' m separator line^-^-------------");

        Optional<Long> valueNoNull = methodNoNull();
        if (valueNoNull.isPresent()) {
            //返回一个不可修改的Set,该set中包含optional实例的所有非空T实例，且在set中每个实例都是单态，如果没有非空实例，则返回一个空的不可以修改的set
            Set<Long> set = valueNoNull.asSet();
            System.out.println(set.size());
            System.out.println(valueNoNull.get());
        } else {
            System.out.println(valueNoNull.or(-6L));
        }
        System.out.println(valueNoNull.orNull());
    }

    private Optional<Long> method() {
        return Optional.fromNullable(null);
    }

    private Optional<Long> methodNoNull() {
        return Optional.fromNullable(6L);
    }
}
