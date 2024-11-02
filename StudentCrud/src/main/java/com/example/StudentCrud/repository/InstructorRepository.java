package com.example.StudentCrud.repository;

import com.example.StudentCrud.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
