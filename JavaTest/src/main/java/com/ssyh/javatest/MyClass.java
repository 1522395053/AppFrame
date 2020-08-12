package com.ssyh.javatest;

import java.util.HashMap;
import java.util.List;

public class MyClass<T> {

    public List<String> list;
    public HashMap  aaa;
    public static void main(String[] args) throws Exception {
//
//        Field list = MyClass.class.getDeclaredField("list");
//        Type genericType = list.getGenericType();
//        System.out.println(genericType);
//
//
//        System.out.println(genericType.getClass().getSimpleName());

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE * -1);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE * -1);

    }
}
