package org.eddie.annotation;

@MyAnnotation(name="class",  value = "I am a class")
public class TheClass {
    @MyAnnotation(name="field",  value = "I am a field")
    private String value;

    @MyAnnotation(name="method",  value = "I am a method")
    public void doSomething(@MyAnnotation(name="param", value="I am a parameter") String parameter){}
}
