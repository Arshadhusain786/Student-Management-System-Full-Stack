package com.example.StudentCrud.service;

import com.example.StudentCrud.domain.Student;
import com.example.StudentCrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> listAll() {
        return repo.findAll();
    }

    public void save(Student std) {
        repo.save(std);
    }

    public Student get(long id) {
        Optional<Student> result = repo.findById(id);
        return result.orElse(null); // Or throw a custom exception
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
