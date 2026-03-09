package org.example.ironschool_homework.service;

import org.example.ironschool_homework.model.Course;
import org.example.ironschool_homework.model.Student;
import org.example.ironschool_homework.model.Teacher;

import java.util.Collection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TeacherService teacherService = new TeacherService();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService(teacherService, studentService);

        System.out.println("===== SCHOOL MANAGEMENT SYSTEM =====");

        System.out.print("Enter school name: ");
        String schoolName = scanner.nextLine();

        while (schoolName.isBlank()) {
            System.out.print("School name cannot be empty. Enter school name again: ");
            schoolName = scanner.nextLine();
        }

        System.out.println("Welcome to " + schoolName + "!");

        createInitialTeachers(scanner, teacherService);
        createInitialCourses(scanner, courseService);
        createInitialStudents(scanner, studentService);

        printMenu();

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Program ended.");
                break;
            }

            try {
                handleCommand(input, teacherService, studentService, courseService);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void createInitialTeachers(Scanner scanner, TeacherService teacherService) {
        int teacherCount = readNonNegativeInt(scanner, "\nHow many teachers do you want to create? ");

        for (int i = 1; i <= teacherCount; i++) {
            System.out.println("\nEnter details for teacher #" + i);

            System.out.print("Name: ");
            String name = scanner.nextLine();

            while (name.isBlank()) {
                System.out.print("Name cannot be empty. Enter again: ");
                name = scanner.nextLine();
            }

            double salary = readNonNegativeDouble(scanner, "Salary: ");

            Teacher teacher = teacherService.createTeacher(name, salary);
            System.out.println("Created teacher -> ID: " + teacher.getTeacherId());
        }
    }

    private static void createInitialCourses(Scanner scanner, CourseService courseService) {
        int courseCount = readNonNegativeInt(scanner, "\nHow many courses do you want to create? ");

        for (int i = 1; i <= courseCount; i++) {
            System.out.println("\nEnter details for course #" + i);

            System.out.print("Name: ");
            String name = scanner.nextLine();

            while (name.isBlank()) {
                System.out.print("Name cannot be empty. Enter again: ");
                name = scanner.nextLine();
            }

            double price = readNonNegativeDouble(scanner, "Price: ");

            Course course = courseService.createCourse(name, price);
            System.out.println("Created course -> ID: " + course.getCourseId());
        }
    }

    private static void createInitialStudents(Scanner scanner, StudentService studentService) {
        int studentCount = readNonNegativeInt(scanner, "\nHow many students do you want to create? ");

        for (int i = 1; i <= studentCount; i++) {
            System.out.println("\nEnter details for student #" + i);

            System.out.print("Name: ");
            String name = scanner.nextLine();
            while (name.isBlank()) {
                System.out.print("Name cannot be empty. Enter again: ");
                name = scanner.nextLine();
            }

            System.out.print("Address: ");
            String address = scanner.nextLine();
            while (address.isBlank()) {
                System.out.print("Address cannot be empty. Enter again: ");
                address = scanner.nextLine();
            }

            System.out.print("Email: ");
            String email = scanner.nextLine();
            while (email.isBlank()) {
                System.out.print("Email cannot be empty. Enter again: ");
                email = scanner.nextLine();
            }

            Student student = studentService.createStudent(name, address, email);
            System.out.println("Created student -> ID: " + student.getStudentId());
        }
    }

    private static void handleCommand(
            String input,
            TeacherService teacherService,
            StudentService studentService,
            CourseService courseService
    ) {
        String[] parts = input.split("\\s+");

        if (input.equalsIgnoreCase("SHOW TEACHERS")) {
            showTeachers(teacherService.getAllTeachers());
            return;
        }

        if (input.equalsIgnoreCase("SHOW STUDENTS")) {
            showStudents(studentService.getAllStudents());
            return;
        }

        if (input.equalsIgnoreCase("SHOW COURSES")) {
            showCourses(courseService.getAllCourses());
            return;
        }

        if (input.equalsIgnoreCase("SHOW PROFIT")) {
            System.out.println("Profit: " + courseService.showProfit());
            return;
        }

        if (input.equalsIgnoreCase("SHOW MONEY EARNED")) {
            System.out.println("Money earned: " + courseService.showTotalMoneyEarned());
            return;
        }

        if (parts.length == 3 && parts[0].equalsIgnoreCase("LOOKUP") && parts[1].equalsIgnoreCase("TEACHER")) {
            Teacher teacher = teacherService.getTeacherById(parts[2]);
            if (teacher == null) {
                System.out.println("Teacher not found.");
            } else {
                printTeacherDetails(teacher);
            }
            return;
        }

        if (parts.length == 3 && parts[0].equalsIgnoreCase("LOOKUP") && parts[1].equalsIgnoreCase("STUDENT")) {
            Student student = studentService.getStudentById(parts[2]);
            if (student == null) {
                System.out.println("Student not found.");
            } else {
                printStudentDetails(student);
            }
            return;
        }

        if (parts.length == 3 && parts[0].equalsIgnoreCase("LOOKUP") && parts[1].equalsIgnoreCase("COURSE")) {
            Course course = courseService.getCourseById(parts[2]);
            if (course == null) {
                System.out.println("Course not found.");
            } else {
                printCourseDetails(course);
            }
            return;
        }

        if (parts.length == 3 && parts[0].equalsIgnoreCase("ASSIGN")) {
            Course updatedCourse = courseService.assignTeacher(parts[1], parts[2]);
            System.out.println("Teacher assigned successfully.");
            printCourseDetails(updatedCourse);
            return;
        }

        if (parts.length == 3 && parts[0].equalsIgnoreCase("ENROLL")) {
            Student updatedStudent = courseService.enrollStudent(parts[1], parts[2]);
            System.out.println("Student enrolled successfully.");
            printStudentDetails(updatedStudent);
            return;
        }

        System.out.println("Unknown command.");
    }

    private static void showTeachers(Collection<Teacher> teachers) {
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }

        System.out.println("\n--- TEACHERS ---");
        for (Teacher teacher : teachers) {
            System.out.println("ID: " + teacher.getTeacherId() +
                    ", Name: " + teacher.getName() +
                    ", Salary: " + teacher.getSalary());
        }
    }

    private static void showStudents(Collection<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- STUDENTS ---");
        for (Student student : students) {
            String courseInfo = student.getCourse() == null
                    ? "No course assigned"
                    : student.getCourse().getName() + " (" + student.getCourse().getCourseId() + ")";

            System.out.println("ID: " + student.getStudentId() +
                    ", Name: " + student.getName() +
                    ", Address: " + student.getAddress() +
                    ", Email: " + student.getEmail() +
                    ", Course: " + courseInfo);
        }
    }

    private static void showCourses(Collection<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n--- COURSES ---");
        for (Course course : courses) {
            String teacherInfo = course.getTeacher() == null
                    ? "No teacher assigned"
                    : course.getTeacher().getName() + " (" + course.getTeacher().getTeacherId() + ")";

            System.out.println("ID: " + course.getCourseId() +
                    ", Name: " + course.getName() +
                    ", Price: " + course.getPrice() +
                    ", Money Earned: " + course.getMoney_earned() +
                    ", Teacher: " + teacherInfo);
        }
    }

    private static void printTeacherDetails(Teacher teacher) {
        System.out.println("\n--- TEACHER DETAILS ---");
        System.out.println("ID: " + teacher.getTeacherId());
        System.out.println("Name: " + teacher.getName());
        System.out.println("Salary: " + teacher.getSalary());
    }

    private static void printStudentDetails(Student student) {
        System.out.println("\n--- STUDENT DETAILS ---");
        System.out.println("ID: " + student.getStudentId());
        System.out.println("Name: " + student.getName());
        System.out.println("Address: " + student.getAddress());
        System.out.println("Email: " + student.getEmail());

        if (student.getCourse() == null) {
            System.out.println("Course: No course assigned");
        } else {
            System.out.println("Course: " + student.getCourse().getName() +
                    " (" + student.getCourse().getCourseId() + ")");
        }
    }

    private static void printCourseDetails(Course course) {
        System.out.println("\n--- COURSE DETAILS ---");
        System.out.println("ID: " + course.getCourseId());
        System.out.println("Name: " + course.getName());
        System.out.println("Price: " + course.getPrice());
        System.out.println("Money earned: " + course.getMoney_earned());

        if (course.getTeacher() == null) {
            System.out.println("Teacher: No teacher assigned");
        } else {
            System.out.println("Teacher: " + course.getTeacher().getName() +
                    " (" + course.getTeacher().getTeacherId() + ")");
        }
    }

    private static int readNonNegativeInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Please enter 0 or a positive integer.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static double readNonNegativeDouble(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Please enter 0 or a positive number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== AVAILABLE COMMANDS =====");
        System.out.println("SHOW TEACHERS");
        System.out.println("LOOKUP TEACHER [TEACHER_ID]");
        System.out.println("SHOW STUDENTS");
        System.out.println("LOOKUP STUDENT [STUDENT_ID]");
        System.out.println("SHOW COURSES");
        System.out.println("LOOKUP COURSE [COURSE_ID]");
        System.out.println("ASSIGN [TEACHER_ID] [COURSE_ID]");
        System.out.println("ENROLL [STUDENT_ID] [COURSE_ID]");
        System.out.println("SHOW PROFIT");
        System.out.println("SHOW MONEY EARNED");
        System.out.println("EXIT");
    }
}
