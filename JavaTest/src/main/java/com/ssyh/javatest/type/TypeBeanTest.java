package com.ssyh.javatest.type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public class TypeBeanTest {
    public static void main(String[] args) {
        Field[] declaredFields = TypeBean.class.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            Type genericType = declaredField.getGenericType();
            System.out.println("变量名：" + declaredField.getName());
            System.out.println("变量声明的类型："+genericType.getTypeName());
            System.out.println("变量声明的真实实现类类型："+genericType.getClass().getName());
            if (genericType instanceof Class) {
                System.out.println("变量类型为Class");
            } else if (genericType instanceof ParameterizedType) {
                System.out.println("变量类型为ParameterizedType");
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                System.out.println("变量类型为ParameterizedType-actualTypeArguments"+actualTypeArguments[0].getClass());
            } else if (genericType instanceof TypeVariable) {
                System.out.println("变量类型为TypeVariable");
            } else if (genericType instanceof GenericArrayType) {
                System.out.println("变量类型为GenericArrayType");
            } else if (genericType instanceof WildcardType) {
                System.out.println("变量类型为WildcardType");
            } else {
                //理论上是没有其他的
                System.out.println("变量类型为 其他");
            }
            System.out.println();
        }
    }
}
