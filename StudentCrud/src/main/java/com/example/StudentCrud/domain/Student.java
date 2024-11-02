package com.example.StudentCrud.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "student") // It's often good practice to use plural for table names
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name", nullable = false, length = 100) // Added column mapping for better control
    private String studentName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Added fetch type and optional=false for mandatory field
    @JoinColumn(name = "course_id", nullable = false) // Ensures the course ID is non-null in DB
    private Course course;




    private Double fee;  // Assuming fee is a Double type; adjust type if different

    // Getter
    public Double getFee() {
        return fee;
    }

    // Setter
    public void setFee(Double fee) {
        this.fee = fee;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
