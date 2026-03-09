package org.example.ironschool_homework.controller;

import jakarta.validation.Valid;
import lombok.Locked;
import org.example.ironschool_homework.model.Teacher;
import org.example.ironschool_homework.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<Collection<Teacher>> findAll() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable String id) {
        Teacher foundTeacher = teacherService.getTeacherById(id);

        if (foundTeacher == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(foundTeacher);
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@Valid @RequestBody Teacher teacher) {
        Teacher newTeacher = teacherService.createTeacher(teacher.getName(), teacher.getSalary());
        return ResponseEntity.status(HttpStatus.CREATED).body(newTeacher);
    }
}
