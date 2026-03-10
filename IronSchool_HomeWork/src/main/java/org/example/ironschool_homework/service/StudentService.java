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


    public Student updateStudentById(String id, String name,String address,String email)  {
        Student student1 = getStudentById(id);
        if (student1 != null) {
            student1.setName(name);
            student1.setAddress(address);
            student1.setEmail(email);
            students.put(student1.getStudentId(), student1);
            return student1;
        }
        throw  new RuntimeException("Student not found");
    }

    public Student patchStudentById(String id, String name,String address,String email) {
        Student student1 = getStudentById(id);
        if (student1 != null) {
            if(name!=null){
                student1.setName(name);
            }
            if(address!=null){
                student1.setAddress(address);
            }
            if(email!=null){
                student1.setEmail(email);
            }
            students.put(student1.getStudentId(), student1);
            return student1;
        }
        throw  new RuntimeException("Student not found");
    }

    public void deleteStudentById(String id) {
       if (!students.containsKey(id)) {
           throw  new RuntimeException("Student not found");
       }
       students.remove(id);
    }

}
