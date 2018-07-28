package org.eddie;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

public class ReflectionTest {
    public static final int COUNT = 100 * 1000 * 1000;

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
