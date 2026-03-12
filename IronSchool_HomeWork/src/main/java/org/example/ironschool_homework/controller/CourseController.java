package org.example.ironschool_homework.controller;

import jakarta.validation.Valid;
import org.example.ironschool_homework.model.Course;
import org.example.ironschool_homework.model.Student;
import org.example.ironschool_homework.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Collection<Course>> findAll() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody Course course) {
        Course newCourse = courseService.createCourse(course.getName(), course.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }

    @PutMapping("/{courseId}/assign/{teacherId}")
    public ResponseEntity<?> assignTeacher(@PathVariable String courseId, @PathVariable String teacherId) {
        Course updatedCourse = courseService.assignTeacher(teacherId, courseId);
        return ResponseEntity.ok(updatedCourse);
    }

    @PutMapping("/{courseId}/enroll/{studentId}")
    public ResponseEntity<?> enrollStudent(@PathVariable String courseId, @PathVariable String studentId) {
        Student updatedStudent = courseService.enrollStudent(studentId, courseId);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/profit")
    public ResponseEntity<Map<String, Double>> showProfit() {
        return ResponseEntity.ok(Map.of("profit", courseService.showProfit()));
    }

    @GetMapping("/money-earned")
    public ResponseEntity<Map<String, Double>> showMoneyEarned() {
        return ResponseEntity.ok(Map.of("money_earned", courseService.showTotalMoneyEarned()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable String id, @Valid @RequestBody Course course) {
        Course currentCourse = courseService.updateCourse(id, course.getPrice(), course.getName());
        return ResponseEntity.ok(currentCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
