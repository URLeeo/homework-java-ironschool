package org.example.ironschool_homework.service;

import org.example.ironschool_homework.exception.CourseNotFoundException;
import org.example.ironschool_homework.exception.StudentAlreadyEnrolledException;
import org.example.ironschool_homework.exception.StudentNotFoundException;
import org.example.ironschool_homework.exception.TeacherNotFoundException;
import org.example.ironschool_homework.model.Course;
import org.example.ironschool_homework.model.Student;
import org.example.ironschool_homework.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CourseService {

    private final Map<String, Course> courses = new HashMap<>();
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CourseService(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public Course createCourse(String name, double price) {
        Course course = new Course(name, price);
        courses.put(course.getCourseId(), course);
        return course;
    }

    public Collection<Course> getAllCourses() {
        return courses.values();
    }

    public Course getCourseById(String id) {
        if (!courses.containsKey(id)) {
            throw new CourseNotFoundException("Course not found");
        }
        return courses.get(id);
    }

    public Course assignTeacher(String teacherId, String courseId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        Course course = getCourseById(courseId);
        course.setTeacher(teacher);
        return course;
    }

    public Student enrollStudent(String studentId, String courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = getCourseById(courseId);
        if (student.getCourse() != null) {
            throw new StudentAlreadyEnrolledException("Student already enrolled in a course!");
        }
        student.setCourse(course);
        course.setMoney_earned(course.getMoney_earned() + course.getPrice());
        return student;
    }

    public double showTotalMoneyEarned() {
        double total = 0;
        for (Course course : courses.values()) {
            total += course.getMoney_earned();
        }
        return total;
    }

    public double showProfit() {
        double totalEarned = showTotalMoneyEarned();
        double totalSalary = 0;

        for (Teacher teacher : teacherService.getAllTeachers()) {
            totalSalary += teacher.getSalary();
        }

        return totalEarned - totalSalary;
    }

    public Course updateCourse(String id, double price, String name) {
        Course course = getCourseById(id);
        course.setName(name);
        course.setPrice(price);
        return course;
    }

    public void deleteCourse(String id) {
        getCourseById(id);
        courses.remove(id);
    }
}
