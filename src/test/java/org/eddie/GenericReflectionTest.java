package org.eddie;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GenericReflectionTest {


    @Test
    public void testMethodReturnTypes() throws Exception {
        Method method = MyClass.class.getMethod("getStringList", null);

        Type returnType = method.getGenericReturnType();

        if(returnType instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            for(Type typeArgument : typeArguments){
                Class typeArgClass = (Class) typeArgument;
                System.out.println("typeArgClass = " + typeArgClass);
            }
        }
    }

    @Test
    public void testMethodParameterTypes() throws Exception {
        Method method = MyClass.class.getMethod("setStringList", List.class);

        Type[] genericParameterTypes = method.getGenericParameterTypes();

        for(Type genericParameterType : genericParameterTypes){
            if(genericParameterType instanceof ParameterizedType){
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] parameterArgTypes = aType.getActualTypeArguments();
                for(Type parameterArgType : parameterArgTypes){
                    Class parameterArgClass = (Class) parameterArgType;
                    System.out.println("parameterArgClass = " + parameterArgClass);
                }
            }
        }
    }

    @Test
    public void testGenericFieldTypes() throws Exception {
        Field field = MyClass.class.getField("stringList");

        Type genericFieldType = field.getGenericType();

        if(genericFieldType instanceof ParameterizedType){
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for(Type fieldArgType : fieldArgTypes){
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("fieldArgClass = " + fieldArgClass);
            }
        }
    }
}
