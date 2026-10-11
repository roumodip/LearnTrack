package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    private String specialty;

    public Trainer(int id, String firstName, String lastName, String email, String specialty) {
        super(id, firstName, lastName, email);
        setSpecialty(specialty);
    }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) {
        this.specialty = requireText(specialty, "Specialty");
    }

    @Override
    public String getDisplayName() {
        return "Trainer " + super.getDisplayName();
    }
}
