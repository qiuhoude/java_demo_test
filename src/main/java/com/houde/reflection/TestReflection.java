package com.houde.reflection;

import org.aspectj.weaver.ast.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by IntelliJ IDEA.
 * User: hodue
 * Date: 2017-06-11
 * Time: 20:53
 */
public class TestReflection {

    public static void classForName() {
        try {
            // 获取 Class 对象
            Class<?> clz = Class.forName("com.houde.reflection.Student");
            // 通过 Class 对象获取 Constructor，Student 的构造函数有一个字符串参数
            // 因此这里需要传递参数的类型 ( Student 类见后面的代码 )
            Constructor<?> constructor = clz.getConstructor(String.class);
            // 通过 Constructor 来创建 Student 对象
            Object obj = constructor.newInstance("mr.simple");
            System.out.println(" obj :  " + obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showDeclaredMethods() {
        Student student = new Student("mr.simple");

        Method[] methods = student.getClass().getDeclaredMethods();

        System.out.println("===========获取类中方法===========");
        for (Method method : methods) {
//            method.setAccessible(false);
            System.out.println("declared method name : " + method.getName());
        }
        System.out.println("===========获取指定方法===========");
        try {
            Method learnMethod = student.getClass().getDeclaredMethod("learn", String.class);
            learnMethod.setAccessible(true);
            // 获取方法的参数类型列表
            Class<?>[] paramClasses = learnMethod.getParameterTypes();
            for (Class<?> class1 : paramClasses) {
                System.out.println("learn 方法的参数类型 : " + class1.getName());
            }
            // 是否是 private 函数，属性是否是 private 也可以使用这种方式判断
            System.out.println(learnMethod.getName() + " is private "
                    + Modifier.isPrivate(learnMethod.getModifiers()));
            learnMethod.invoke(student, "java ---> ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMethods() {
        Student student = new Student("houde");
        Method[] methods = student.getClass().getMethods();
        System.out.println("===========获取自己和父类中方法===========");
        for (Method method : methods) {
            System.out.println("method name : " + method.getName());
        }
        System.out.println("======================");
        try {
            // 通过 getMethod 只能获取公有方法，如果获取私有方法则会抛出异常，比如这里就会抛异常
            Method learnMethod = student.getClass().getMethod("learn", String.class);
            // 是否是 private 函数，属性是否是 private 也可以使用这种方式判断
            System.out.println(learnMethod.getName() + " is private " + Modifier.isPrivate(learnMethod.getModifiers()));
            // 调用 learn 函数
            learnMethod.invoke(student, "java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void showDeclaredFields() {
        Student student = new Student("mr.simple");
        // 获取当前类和父类的所有公有属性
        Field[] publicFields = student.getClass().getDeclaredFields();
        for (Field field : publicFields) {
            System.out.println("declared field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有属性
            Field gradeField = student.getClass().getDeclaredField("mGrade");
            // 获取属性值
            System.out.println(" my grade is : " + gradeField.getInt(student));
            // 设置属性值
            gradeField.set(student, 10);
            System.out.println(" my grade is : " + gradeField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showFields() {
        Student student = new Student("mr.simple");
        // 获取当前类和父类的所有公有属性
        Field[] publicFields = student.getClass().getFields();
        for (Field field : publicFields) {
            System.out.println("field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有属性
            Field ageField = student.getClass().getField("mAge");
            System.out.println(" age is : " + ageField.getInt(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSuperclass() {

        Student student = new Student("mr.simple");
        Class<?> superClass = student.getClass().getSuperclass();
        while (superClass != null) {
            System.out.println("Student's super class is : " + superClass.getName());
            // 再获取父类的上一层父类，直到最后的 Object 类，Object 的父类为 null
            superClass = superClass.getSuperclass();
        }
    }

    private static void showInterfaces() {
        Student student = new Student("mr.simple");
        Class<?>[] interfaceses = student.getClass().getInterfaces();
        for (Class<?> class1 : interfaceses) {
            System.out.println("Student's interface is : " + class1.getName());
            Method[] methods = class1.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("接口的方法有 " + method.getName());
            }
        }
    }

    private static void getAnnotationInfos() {
        Student student = new Student("mr.simple");
        MyAnnotation classTest = student.getClass().getAnnotation(MyAnnotation.class);
        System.out.println("class Annotatation tag = " + classTest.tag()[0]);

        Field field = null;
        try {
            field = student.getClass().getDeclaredField("mGrade");
            MyAnnotation testAnnotation = field.getAnnotation(MyAnnotation.class);
            System.out.println("属性的 Test 注解 tag : " + testAnnotation.tag()[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        showMethods();
//        showFields();
//        showDeclaredFields();
//        showSuperclass();
//        showInterfaces();
        getAnnotationInfos();
    }
}
