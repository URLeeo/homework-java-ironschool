package org.example.ironschool_homework.service;

import org.example.ironschool_homework.model.Teacher;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherService {

    private Map<String, Teacher> Teachers = new HashMap<>();

    public void createTeacher(String name, double salary){
        Teacher teacher = new Teacher(name,salary);
        Teachers.put(teacher.getTeacherId(),teacher);
    }

    public Collection<Teacher> getAllTeachers(){
        return Teachers.values();
    }
}
