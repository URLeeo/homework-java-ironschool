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

    public Teacher updateTeacherById(String id, String name, double salary) {
        Teacher oldTeacher = getTeacherById(id);
        if (oldTeacher != null) {
           oldTeacher.setName(name);
           oldTeacher.setSalary(salary);
           teachers.put(oldTeacher.getTeacherId(), oldTeacher);
           return oldTeacher;
        }
        throw new RuntimeException("Teacher not found");
    }

    public Teacher patchTeacherById(String id, String name, double salary) {
        Teacher oldTeacher = getTeacherById(id);
        if (oldTeacher != null) {
            if(name!=null){
                oldTeacher.setName(name);
            }
            teachers.put(oldTeacher.getTeacherId(), oldTeacher);
            return oldTeacher;

        }
        throw new RuntimeException("Teacher not found");
    }

    public void deleteTeacherById(String id) {
        if (!teachers.containsKey(id)) {
            throw new RuntimeException("Teacher not found");
        }
        teachers.remove(id);
    }
}
