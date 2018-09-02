package org.eddie;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class DynamicProxyTest {
    private class TimingDynamicInvocationHandler implements InvocationHandler {

        private final Map<String, Method> methods = new HashMap<>();

        private Object target;

        public TimingDynamicInvocationHandler(Object target) {
            this.target = target;

            for (Method method : target.getClass().getDeclaredMethods()) {
                this.methods.put(method.getName(), method);
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.nanoTime();
            Object result = methods.get(method.getName()).invoke(target, args);
            long elapsed = System.nanoTime() - start;

            System.out.println(String.format("Executing %s finished in %d ns", method.getName(), elapsed));

            return result;
        }
    }

    @Test
    public void testCreatingProxies() throws Exception {

        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                DynamicProxyTest.class.getClassLoader(), new Class[]{Map.class},
                new TimingDynamicInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world");
        mapProxyInstance.get("hello");
        mapProxyInstance.putIfAbsent("foo", "bar");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                DynamicProxyTest.class.getClassLoader(),
                new Class[]{CharSequence.class},
                new TimingDynamicInvocationHandler("Hello World"));

        csProxyInstance.length();
    }
}
