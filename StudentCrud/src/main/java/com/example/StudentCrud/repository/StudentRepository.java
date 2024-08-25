package com.example.StudentCrud.repository;

import com.example.StudentCrud.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long>
{

}
