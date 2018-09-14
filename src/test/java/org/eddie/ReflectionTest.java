package org.eddie;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.stream.IntStream;

public class ReflectionTest {
    public static final int COUNT = 100 * 1000 * 1000;

    @Test
    public void method() throws Exception {
        for (Method method : MyClass.class.getMethods()) {
            System.out.println(method.getName() + ":" + method.getReturnType());
            for (Parameter parameter : method.getParameters()) {
                System.out.println("\t" + parameter.getName() + ":" + parameter.getParameterizedType().getTypeName());
            }
        }
    }

    @Test
    public void fields() throws Exception {
        for (Field field : MyClass.class.getFields()) {
            System.out.println(field.getName() + ":" + field.getType());
        }
    }

    @Test
    public void invokeMethod() throws Exception {
        Method getInstanceMethod = MyClass.class.getMethod("getInstance", String.class);
        Object test = getInstanceMethod.invoke(null, "Test");
        System.out.println(ToStringBuilder.reflectionToString(test));

        Method getStringListMethod = MyClass.class.getMethod("getStringList");
        System.out.println(getStringListMethod.invoke(test, null));
    }

    @Test
    public void privateField() throws Exception {
        MyClass myClass = new MyClass("123");
        Field privateStringField = MyClass.class.
                getDeclaredField("privateString");
        privateStringField.setAccessible(true);
        String fieldValue = (String) privateStringField.get(myClass);
        System.out.println("fieldValue = " + fieldValue);
    }

    @Test
    public void testNonReflection() throws Exception {
        IntStream.range(0, COUNT).forEach(value -> {
            nonReflection();
        });
    }

    @Test
    public void testConstructor() throws Exception {
        IntStream.range(0, COUNT).forEach(value -> {
            try {
                createByConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testCacheConstructor() throws Exception {
        Constructor<User> constructor = User.class.getConstructor(String.class, String.class);
        Method getName = User.class.getMethod("getName");
        Method getPassword = User.class.getMethod("getPassword");
        IntStream.range(0, COUNT).forEach(value -> {
            try {
                createByCacheConstructor(constructor, getName, getPassword);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private void nonReflection() {
        User user = new User("jason", "pwd");
        Assert.assertEquals("jason", user.getName());
        Assert.assertEquals("pwd", user.getPassword());
    }

    private void createByConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Constructor<User> constructor = User.class.getConstructor(String.class, String.class);
        Method getName = User.class.getMethod("getName");
        Method getPassword = User.class.getMethod("getPassword");
        User user = constructor.newInstance("jason", "pwd");
        Assert.assertEquals("jason", getName.invoke(user));
        Assert.assertEquals("pwd", getPassword.invoke(user));
    }

    private void createByCacheConstructor(Constructor<User> constructor, Method getName, Method getPassword) throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        User user = constructor.newInstance("jason", "pwd");

        Assert.assertEquals("jason", getName.invoke(user));
        Assert.assertEquals("pwd", getPassword.invoke(user));
    }
}
