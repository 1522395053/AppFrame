package com.ssyh.javatest.type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Set;

public class TypeTest<T,V,M,N,O,t,m> {
    private List<t> listT = null;
    private Set<T> setT = null;
    TypeTest typeTest;
    T[] tarr;
    List<String>[] lists;
    List<T>[] listsT;
    Integer integer;
    int anInt;
    Type type;
    List<?> listQA;
    List<? extends TypeTest> listQAextends;
    List<?   super TypeTest> listQAsuper;

    public static void main(String[] args) throws NoSuchFieldException {
        Field declaredField = TypeTest.class.getDeclaredField("listQAsuper");
//        Class<?> type = declaredFieldList.getType();
        Type genericType = declaredField.getGenericType();

//        System.out.println("getType---: "+ type.getName());
//        System.out.println("getType---: "+ type.getTypeName());
        System.out.println("getTypeName---: "+genericType.getTypeName());
        System.out.println("getClass().getName()---: "+genericType.getClass().getName());
        if (genericType instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type ownerType = parameterizedType.getOwnerType();
            Type rawType = parameterizedType.getRawType();

            Type actualTypeArgument = actualTypeArguments[0];
            System.out.println("actualTypeArguments-getTypeName---: "+actualTypeArgument.getTypeName());
            System.out.println("actualTypeArguments-getClass.getName---: "+actualTypeArgument.getClass().getName());
            if (actualTypeArgument instanceof TypeVariable){
                TypeVariable typeVariable = (TypeVariable) actualTypeArgument;
                Type[] bounds = typeVariable.getBounds();
                GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
                String name = typeVariable.getName();
                System.out.println("typeVariable-bounds-getTypeName---: "+bounds[0].getTypeName());
                System.out.println("typeVariable-bounds-getClass.getName---: "+bounds[0].getClass().getName());
                System.out.println("typeVariable-genericDeclaration: "+genericDeclaration);
                System.out.println("typeVariable-genericDeclaration-getTypeParameters-0: "+genericDeclaration.getTypeParameters()[0].getName());
                System.out.println("typeVariable-genericDeclaration-getTypeParameters-1: "+genericDeclaration.getTypeParameters()[1].getName());
                System.out.println("typeVariable-genericDeclaration-getTypeParameters-2: "+genericDeclaration.getTypeParameters()[2].getName());
                System.out.println("typeVariable-name: "+name);
            }
            if (actualTypeArgument instanceof WildcardType){
                WildcardType wildcardType = (WildcardType) actualTypeArgument;
                Type[] lowerBounds = wildcardType.getLowerBounds();
                Type[] upperBounds = wildcardType.getUpperBounds();


                if (lowerBounds.length >0)
                    System.out.println("WildcardType-lowerBounds----: "+lowerBounds[0]);
                System.out.println("WildcardType-upperBounds----: "+upperBounds[0]);
                System.out.println("WildcardType-typeName----: "+wildcardType.getTypeName());
                System.out.println("WildcardType-getClass.getName----: "+wildcardType.getClass().getName());
            }
            if (null != ownerType){
                System.out.println("ownerType-getTypeName---: "+ownerType.getTypeName());
                System.out.println("ownerType-getClass.getName---: "+ownerType.getClass().getName());
            }else {
                System.out.println("ownerType: null");
            }

            System.out.println("rawType-getTypeName---: "+rawType.getTypeName());
            System.out.println("rawType-getClass.getName---: "+rawType.getClass().getName());
        }


        if (genericType instanceof GenericArrayType){
            GenericArrayType genericArrayType = (GenericArrayType) genericType;
            Type genericComponentType = genericArrayType.getGenericComponentType();

            System.out.println("genericComponentType-getTypeName---: "+genericComponentType.getTypeName());
            System.out.println("genericComponentType-getClass.getName---: "+genericComponentType.getClass().getName());
        }

//
//
//
//        declaredField = TypeTest.class.getDeclaredField("setT");
////        Class<?> type = declaredFieldList.getType();
//        genericType = declaredField.getGenericType();
//
////        System.out.println("getType---: "+ type.getName());
////        System.out.println("getType---: "+ type.getTypeName());
//        System.out.println("getTypeName---: "+genericType.getTypeName());
//        System.out.println("getClass().getName()---: "+genericType.getClass().getName());

    }
}
