package org.example.ironschool_homework.model;

import java.util.UUID;

public class Student {
    private String studentId;
    private String name;
    private String address;
    private String email;
    private Course course;
    private static int counter=1;

    public Student(String name, String address, String email) {
        this.studentId = "T"+counter++;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Student(String studentId, String name, String address, String email, Course course) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
