package com.example.ameerhamza.firebabseyoutubetest;

/**
 * Created by AmeerHamza on 9/18/2016.
 */
public class Student {


    private  String name;
    private String emailId;
    private int age;

    public Student(String emailId, int age, String name) {
        this.emailId = emailId;
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmailId() {
        return emailId;
    }

    public Student() {
    }
}
