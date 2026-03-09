package org.example.ironschool_homework.service;

import org.example.ironschool_homework.model.Student;
import org.example.ironschool_homework.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final Map<String, Student> students = new HashMap<>();

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Student createStudent(String name, String address, String email) {
        Student student = new Student(name, address, email);
        students.put(student.getStudentId(), student);
        return student;
    }

    public Student getStudentById(String id) {
        return students.get(id);
    }
}
