package com.houde.reflection;

/**
 * Created by IntelliJ IDEA.
 * User: hodue
 * Date: 2017-06-11
 * Time: 17:53
 */
public class Person implements Breathe {
    String mName;

    public Person(String aName) {
        mName = aName;
    }

    private void sayHello(String friendName) {
        System.out.println(mName + " say hello to " + friendName);
    }

    protected void showMyName() {
        System.out.println("My name is " + mName);
    }

    public void breathe() {
        System.out.println(" take breathe ");
    }
}
