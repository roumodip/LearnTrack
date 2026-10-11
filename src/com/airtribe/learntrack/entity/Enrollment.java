package com.airtribe.learntrack.entity;

import java.time.LocalDate;

public class Enrollment {
    public enum Status { ACTIVE, COMPLETED, CANCELLED }

    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private Status status;

    public Enrollment() {
        this(0, 0, 0, LocalDate.now(), Status.ACTIVE);
    }

    public Enrollment(int id, int studentId, int courseId, LocalDate enrollmentDate, Status status) {
        setId(id);
        setStudentId(studentId);
        setCourseId(courseId);
        setEnrollmentDate(enrollmentDate);
        setStatus(status);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID cannot be negative.");
        this.id = id;
    }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) {
        if (studentId <= 0) throw new IllegalArgumentException("Student ID must be positive.");
        this.studentId = studentId;
    }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) {
        if (courseId <= 0) throw new IllegalArgumentException("Course ID must be positive.");
        this.courseId = courseId;
    }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        if (enrollmentDate == null) throw new IllegalArgumentException("Enrollment date is required.");
        this.enrollmentDate = enrollmentDate;
    }
    public Status getStatus() { return status; }
    public void setStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("Enrollment status is required.");
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Enrollment ID: %d | Student ID: %d | Course ID: %d | Date: %s | Status: %s",
                id, studentId, courseId, enrollmentDate, status);
    }
}
