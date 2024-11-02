package com.example.StudentCrud.repository;

import com.example.StudentCrud.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
