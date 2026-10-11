package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(String name, String description, int durationInWeeks) {
        Course course = new Course(IdGenerator.getNextCourseId(), name, description, durationInWeeks);
        courses.add(course);
        return course;
    }

    public List<Course> listCourses() { return new ArrayList<>(courses); }

    public Course findCourseById(int id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId() == id) return course;
        }
        throw new EntityNotFoundException("Course with ID " + id + " was not found.");
    }

    public Course updateCourse(int id, String name, String description, int durationInWeeks)
            throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setCourseName(name);
        course.setDescription(description);
        course.setDurationInWeeks(durationInWeeks);
        return course;
    }

    public Course deactivateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(false);
        return course;
    }

    public Course activateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(true);
        return course;
    }
}
