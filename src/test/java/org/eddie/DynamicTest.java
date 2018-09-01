package org.eddie;

import org.junit.Test;

import java.util.Arrays;

public class DynamicTest {
    @Test
    public void name() throws Exception {
        Arrays.stream(User.class.getMethods()).forEach(method -> {
            System.out.print(method.getName() + "\t");
            Arrays.stream(method.getParameters()).forEach(parameter -> {
                System.out.print(parameter.getName() + ":" + parameter.getType().getName() + "\t");
            });
            System.out.println();
        });
    }
}
