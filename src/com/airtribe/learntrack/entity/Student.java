package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        this(0, "Unknown", "Student", "", "Unassigned", true);
    }

    public Student(int id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, "", batch, true);
    }

    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        setBatch(batch);
        this.active = active;
    }

    public String getBatch() { return batch; }
    public void setBatch(String batch) {
        this.batch = requireText(batch, "Batch");
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Student #" + getId() + ")";
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Email: %s | Batch: %s | Active: %s",
                getId(), getDisplayName(), getEmail().isBlank() ? "N/A" : getEmail(), batch, active);
    }
}
