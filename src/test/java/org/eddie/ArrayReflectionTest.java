package org.eddie;

import org.junit.Test;

import java.lang.reflect.Array;

public class ArrayReflectionTest {
    @Test
    public void createAndAccessArray() throws Exception {
        int[] intArray = (int[]) Array.newInstance(int.class, 3);

        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));
    }

    @Test
    public void testObtainingTheClassObjectOfAnArray() throws Exception {
        Class stringArrayClass = String[].class;
        Class intArray = Class.forName("[I");
        Class stringArrayClass1 = Class.forName("[Ljava.lang.String;");
        System.out.println(stringArrayClass.getName());
        System.out.println(intArray.getName());
        System.out.println(stringArrayClass1.getName());
    }

    @Test
    public void testCreateIntArray() throws Exception {
        Class intClass = getClass("int");
        Class<?> intArrayClass = Array.newInstance(intClass, 0).getClass();
        System.out.println(intArrayClass.getName());
    }

    @Test
    public void testCreateMyClassArray() throws Exception {
        Class myClass = getClass("org.eddie.MyClass");
        Class<?> myClassArrayClass = Array.newInstance(myClass, 0).getClass();
        System.out.println(myClassArrayClass.getName());
    }

    @Test
    public void testObtainingTheComponentTypeOfAnArray() throws Exception {
        String[] strings = new String[3];
        Class stringArrayClass = strings.getClass();
        Class stringArrayComponentType = stringArrayClass.getComponentType();
        System.out.println(stringArrayComponentType);
    }

    private Class getClass(String className) throws ClassNotFoundException {
        if("int" .equals(className)) return int.class;
        if("boolean" .equals(className)) return boolean.class;
        if("long".equals(className)) return long.class;
        return Class.forName(className);
    }
}
