package org.example.ironschool_homework.controller;

import jakarta.validation.Valid;
import org.example.ironschool_homework.model.Student;
import org.example.ironschool_homework.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        Student newStudent = studentService.createStudent(
                student.getName(),
                student.getAddress(),
                student.getEmail()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(String id,@Valid @RequestBody Student student) {
        Student student1=studentService.updateStudentById(id,student.getName(),student.getAddress(),student.getEmail());
        return ResponseEntity.ok(student1);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Student> partial(@Valid @RequestBody Student student,@PathVariable String id) {
        Student student1=studentService.patchStudentById(id,student.getName(),student.getAddress(),student.getEmail());
        return ResponseEntity.ok(student1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable String id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
