package com.example.StudentCrud.controller;

import com.example.StudentCrud.domain.Course;
import com.example.StudentCrud.domain.Instructor;
import com.example.StudentCrud.domain.Student;
import com.example.StudentCrud.service.CourseService;
import com.example.StudentCrud.service.InstructorService;
import com.example.StudentCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students") // Base mapping for all student operations
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final InstructorService instructorService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, InstructorService instructorService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    // Home page with list of students
    @GetMapping
    public String viewHomePage(Model model) {
        List<Student> listStudents = studentService.listAll();
        model.addAttribute("listStudents", listStudents);
        model.addAttribute("listCourses", courseService.listAll());
        return "index"; // index.html in the templates folder
    }

    // Form for adding a new student
    @GetMapping("/new")
    public String addStudentForm(Model model) {
        Student student = new Student();
        List<Course> listCourses = courseService.listAll(); // Assuming you're also populating courses
        List<Instructor> instructors = instructorService.listAll(); // Fetch the list of instructors
        model.addAttribute("student", student);
        model.addAttribute("listCourses", listCourses);
        model.addAttribute("instructors", instructors); // Add instructors to the model
        return "new"; // Return the view name
    }


    // Save student data from the form
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        try {
            studentService.save(student);
            redirectAttributes.addFlashAttribute("message", "Student saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to save student: " + e.getMessage());
        }
        return "redirect:/students"; // Redirects to home page listing all students
    }

    // Form for editing an existing student
    @GetMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("new"); // Use the same form for creating/editing
        Student student = studentService.get(id);
        List<Course> listCourses = courseService.listAll();
        mav.addObject("student", student);
        mav.addObject("listCourses", listCourses);
        return mav;
    }

    // Delete a student by ID
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete student: " + e.getMessage());
        }
        return "redirect:/students";
    }


}



