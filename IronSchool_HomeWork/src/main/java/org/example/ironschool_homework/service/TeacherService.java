package org.example.ironschool_homework.service;

import org.example.ironschool_homework.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    private final Map<String, Teacher> teachers = new HashMap<>();

    public Teacher createTeacher(String name, double salary) {
        Teacher teacher = new Teacher(name, salary);
        teachers.put(teacher.getTeacherId(), teacher);
        return teacher;
    }

    public Collection<Teacher> getAllTeachers() {
        return teachers.values();
    }

    public Teacher getTeacherById(String id) {
        return teachers.get(id);
    }
}
