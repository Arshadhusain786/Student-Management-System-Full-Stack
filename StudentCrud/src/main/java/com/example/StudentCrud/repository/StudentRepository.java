package com.example.StudentCrud.repository;

import com.example.StudentCrud.domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student,Long>
{
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.course = NULL WHERE s.course.id = :courseId")
    void updateCourseIdToNull(@Param("courseId") Long courseId);

}
