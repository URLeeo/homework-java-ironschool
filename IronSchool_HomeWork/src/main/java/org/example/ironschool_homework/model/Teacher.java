package org.example.ironschool_homework.model;

public class Teacher {
    private static int counter = 1;
    private String teacherId;
    private String name;
    private double salary;


    public Teacher(String name, double salary) {
        this.teacherId = "T" + counter++;
        this.name = name;
        this.salary = salary;
    }

    public Teacher(String teacherId, String name, double salary) {
        this.teacherId = teacherId;
        this.name = name;
        this.salary = salary;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
