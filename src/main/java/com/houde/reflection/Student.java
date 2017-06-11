package com.houde.reflection;

/**
 * Created by IntelliJ IDEA.
 * User: hodue
 * Date: 2017-06-11
 * Time: 17:56
 */
@MyAnnotation(tag = "Student class Test Annoatation")
public class Student extends Person implements Examination {

    // 年级
    @MyAnnotation(tag = "mGrade Test Annotation ")
    int mGrade;

    public Student(String aName) {
        super(aName);
    }

    public Student(int grade, String aName) {
        super(aName);
        mGrade = grade;
    }

    private void learn(String course) {
        System.out.println(mName + " learn " + course);
    }

    public void takeAnExamination() {
        System.out.println(" takeAnExamination ");
    }

    public String toString() {
        return " Student :  " + mName;
    }
}
