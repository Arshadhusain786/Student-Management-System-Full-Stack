package com.example.StudentCrud.service;

import com.example.StudentCrud.domain.Course;
import com.example.StudentCrud.domain.Instructor;
import com.example.StudentCrud.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Method to create and save a new course
    public Course createCourse(String title, Instructor instructor) {
        Course course = new Course();
        course.setTitle(title);
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    // Method to list all courses
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    // Method to save or update a course
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    // Method to get a course by ID
    public Course get(long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));
    }

    // Method to delete a course by ID
    public void delete(long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id " + id);
        }
        courseRepository.deleteById(id);
    }
}



