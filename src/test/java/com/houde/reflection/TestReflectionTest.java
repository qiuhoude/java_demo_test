package com.houde.reflection;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: hodue
 * Date: 2017-06-11
 * Time: 21:18
 */
public class TestReflectionTest {
    @Test
    public void classForName() throws Exception {
            TestReflection.classForName();
    }

    @Test
    public void showDeclaredMethods() throws Exception {
        TestReflection.showDeclaredMethods();
    }

}