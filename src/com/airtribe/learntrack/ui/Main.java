package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("==================================");
        System.out.println("       Welcome to LearnTrack");
        System.out.println("==================================");
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> studentMenu();
                    case 2 -> courseMenu();
                    case 3 -> enrollmentMenu();
                    case 0 -> {
                        running = false;
                        System.out.println("Thank you for using LearnTrack. Goodbye!");
                    }
                    default -> System.out.println("Invalid menu option. Choose 0, 1, 2, or 3.");
                }
            } catch (EntityNotFoundException | IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Something went wrong: " + ex.getMessage());
            }
        }
        scanner.close();
    }

    private void printMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
    }

    private void studentMenu() throws EntityNotFoundException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Deactivate student");
            System.out.println("6. Activate student");
            System.out.println("0. Back");
            switch (readInt("Choose an option: ")) {
                case 1 -> addStudent();
                case 2 -> printList(studentService.listStudents(), "No students found.");
                case 3 -> System.out.println(studentService.findStudentById(readInt("Student ID: ")));
                case 4 -> updateStudent();
                case 5 -> System.out.println("Updated: " + studentService.deactivateStudent(readInt("Student ID: ")));
                case 6 -> System.out.println("Updated: " + studentService.activateStudent(readInt("Student ID: ")));
                case 0 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addStudent() {
        String first = readText("First name: ");
        String last = readText("Last name: ");
        String email = readOptionalText("Email (optional): ");
        String batch = readText("Batch: ");
        Student student = studentService.addStudent(first, last, email, batch);
        System.out.println("Student added successfully: " + student);
    }

    private void updateStudent() throws EntityNotFoundException {
        int id = readInt("Student ID to update: ");
        String first = readText("New first name: ");
        String last = readText("New last name: ");
        String email = readOptionalText("New email (optional): ");
        String batch = readText("New batch: ");
        System.out.println("Student updated: " + studentService.updateStudent(id, first, last, email, batch));
    }

    private void courseMenu() throws EntityNotFoundException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- COURSE MANAGEMENT ---");
            System.out.println("1. Add course");
            System.out.println("2. List courses");
            System.out.println("3. Search course by ID");
            System.out.println("4. Update course");
            System.out.println("5. Deactivate course");
            System.out.println("6. Activate course");
            System.out.println("0. Back");
            switch (readInt("Choose an option: ")) {
                case 1 -> addCourse();
                case 2 -> printList(courseService.listCourses(), "No courses found.");
                case 3 -> System.out.println(courseService.findCourseById(readInt("Course ID: ")));
                case 4 -> updateCourse();
                case 5 -> System.out.println("Updated: " + courseService.deactivateCourse(readInt("Course ID: ")));
                case 6 -> System.out.println("Updated: " + courseService.activateCourse(readInt("Course ID: ")));
                case 0 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addCourse() {
        String name = readText("Course name: ");
        String description = readOptionalText("Description (optional): ");
        int duration = readInt("Duration in weeks: ");
        System.out.println("Course added successfully: " + courseService.addCourse(name, description, duration));
    }

    private void updateCourse() throws EntityNotFoundException {
        int id = readInt("Course ID to update: ");
        String name = readText("New course name: ");
        String description = readOptionalText("New description (optional): ");
        int duration = readInt("New duration in weeks: ");
        System.out.println("Course updated: " + courseService.updateCourse(id, name, description, duration));
    }

    private void enrollmentMenu() throws EntityNotFoundException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- ENROLLMENT MANAGEMENT ---");
            System.out.println("1. Enroll student in course");
            System.out.println("2. List all enrollments");
            System.out.println("3. View enrollments for a student");
            System.out.println("4. Mark enrollment completed");
            System.out.println("5. Cancel enrollment");
            System.out.println("0. Back");
            switch (readInt("Choose an option: ")) {
                case 1 -> enrollStudent();
                case 2 -> printList(enrollmentService.listEnrollments(), "No enrollments found.");
                case 3 -> {
                    int studentId = readInt("Student ID: ");
                    printList(enrollmentService.findEnrollmentsByStudentId(studentId),
                            "No enrollments found for this student.");
                }
                case 4 -> System.out.println("Updated: " + enrollmentService.updateStatus(
                        readInt("Enrollment ID: "), Enrollment.Status.COMPLETED));
                case 5 -> System.out.println("Updated: " + enrollmentService.updateStatus(
                        readInt("Enrollment ID: "), Enrollment.Status.CANCELLED));
                case 0 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void enrollStudent() throws EntityNotFoundException {
        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");
        System.out.println("Enrollment created: " + enrollmentService.enrollStudent(studentId, courseId));
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    private String readOptionalText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void printList(List<?> items, String emptyMessage) {
        if (items.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Object item : items) System.out.println(item);
    }
}
