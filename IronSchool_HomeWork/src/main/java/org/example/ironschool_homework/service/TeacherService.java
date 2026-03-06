package org.example.ironschool_homework.service;

import org.example.ironschool_homework.model.Teacher;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherService {

    private Map<String, Teacher> Teachers = new HashMap<>();

    public void createTeacher(String teacherId, String name, double salary){
        Teacher teacher = new Teacher(teacherId,name,salary);
        Teachers.put(teacherId,teacher);
    }

    public Collection<Teacher> getAllTeachers(){
        Collection<Teacher> teachers = Teachers.values();
        return teachers;
    }
}
