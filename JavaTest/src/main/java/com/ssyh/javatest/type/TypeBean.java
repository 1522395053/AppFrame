package com.ssyh.javatest.type;


import java.util.List;
import java.util.Map;

public class TypeBean<K,T> {
    private T t;
    private String string;
    private Map<String,String> map;
    private Map<K,T> map2;
    private List<T> list;
    private List<String > list2;
    private List<? extends String> listQAString;
    private int aint;
    private T[] tArray;
    private int[] intAarry;
    private Integer[] integers;
    private List<String>[] lists;
    private Class<?> clazz;
    private EnumTest enumTest;
    private InterfaceTest interfaceTest;
    private AnnotationTest annotationTest;
}