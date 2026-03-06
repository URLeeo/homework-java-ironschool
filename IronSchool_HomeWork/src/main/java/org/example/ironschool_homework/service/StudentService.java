package org.example.ironschool_homework.service;

import org.example.ironschool_homework.model.Student;
import org.example.ironschool_homework.model.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private final Map<String, Student> students = new HashMap<>();

    public List<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

    public Student addStudent(String studentId, String name, String address, String email) {
        Student student = new Student(name, address, email);
        students.put(studentId, student);
        return student;

    }



}
