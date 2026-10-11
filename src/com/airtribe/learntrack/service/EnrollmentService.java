package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) throws EntityNotFoundException {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);
        if (!student.isActive()) throw new IllegalArgumentException("Cannot enroll an inactive student.");
        if (!course.isActive()) throw new IllegalArgumentException("Cannot enroll in an inactive course.");

        for (Enrollment existing : enrollments) {
            if (existing.getStudentId() == studentId && existing.getCourseId() == courseId
                    && existing.getStatus() == Enrollment.Status.ACTIVE) {
                throw new IllegalArgumentException("Student already has an active enrollment for this course.");
            }
        }

        Enrollment enrollment = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId, courseId,
                LocalDate.now(), Enrollment.Status.ACTIVE);
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> listEnrollments() { return new ArrayList<>(enrollments); }

    public List<Enrollment> findEnrollmentsByStudentId(int studentId) throws EntityNotFoundException {
        studentService.findStudentById(studentId);
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) result.add(enrollment);
        }
        return result;
    }

    public Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) return enrollment;
        }
        throw new EntityNotFoundException("Enrollment with ID " + id + " was not found.");
    }

    public Enrollment updateStatus(int enrollmentId, Enrollment.Status status) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(status);
        return enrollment;
    }
}
