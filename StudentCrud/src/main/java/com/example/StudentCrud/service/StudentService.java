package com.example.StudentCrud.service;

import com.example.StudentCrud.domain.Student;
import com.example.StudentCrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    @Autowired
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> listAll() {
        return repo.findAll();
    }

    public void save(Student student) {
        repo.save(student);
    }

    public Student get(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        // Consider creating a custom exception class for more flexibility
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}


