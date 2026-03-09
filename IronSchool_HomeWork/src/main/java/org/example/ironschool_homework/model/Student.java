package org.example.ironschool_homework.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Student {
    private static int counter = 1;

    private String studentId;

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @NotBlank(message = "Address cannot be empty!")
    private String address;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Email format is invalid!")
    private String email;

    private Course course;

    public Student(String name, String address, String email) {
        this.studentId = "S" + counter++;
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

    public Student() {
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