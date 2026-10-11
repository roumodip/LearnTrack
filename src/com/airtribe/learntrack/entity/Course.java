package com.airtribe.learntrack.entity;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course() { }

    public Course(int id, String courseName, String description, int durationInWeeks) {
        this(id, courseName, description, durationInWeeks, true);
    }

    public Course(int id, String courseName, String description, int durationInWeeks, boolean active) {
        setId(id);
        setCourseName(courseName);
        setDescription(description);
        setDurationInWeeks(durationInWeeks);
        this.active = active;
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID cannot be negative.");
        this.id = id;
    }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) {
        this.courseName = requireText(courseName, "Course name");
    }
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }
    public int getDurationInWeeks() { return durationInWeeks; }
    public void setDurationInWeeks(int durationInWeeks) {
        if (durationInWeeks <= 0) throw new IllegalArgumentException("Duration must be greater than zero.");
        this.durationInWeeks = durationInWeeks;
    }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    private String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(fieldName + " cannot be empty.");
        return value.trim();
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Course: %s | Duration: %d weeks | Active: %s | Description: %s",
                id, courseName, durationInWeeks, active, description.isBlank() ? "N/A" : description);
    }
}
