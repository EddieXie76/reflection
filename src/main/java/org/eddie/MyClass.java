package org.eddie;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

    public List<String> stringList = new ArrayList<>();

    private String privateString = null;

    public MyClass() {
    }

    public MyClass(String privateString) {
        this.privateString = privateString;
    }

    public static MyClass getInstance(String str){
        MyClass myClass = new MyClass();
        myClass.stringList.add(str);
        return myClass;
    }

    public List<String> getStringList() {
        return this.stringList;
    }

    public void setStringList(List<String> list) {
        this.stringList = list;
    }
}