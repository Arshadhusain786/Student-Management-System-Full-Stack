package com.example.StudentCrud.service;

import com.example.StudentCrud.domain.Instructor;
import com.example.StudentCrud.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepo;

    public List<Instructor> listAll() {
        return instructorRepo.findAll();
    }

    @Transactional
    public void save(Instructor instructor) {
        instructorRepo.save(instructor);
    }

    public Instructor get(long id) {
        Optional<Instructor> result = instructorRepo.findById(id);
        return result.orElse(null); // Or throw a custom exception
    }

    public void delete(long id) {
        instructorRepo.deleteById(id);
    }


}

