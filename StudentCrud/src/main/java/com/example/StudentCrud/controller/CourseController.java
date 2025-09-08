package com.example.StudentCrud.controller;

import com.example.StudentCrud.domain.Course;
import com.example.StudentCrud.domain.Instructor;
import com.example.StudentCrud.service.CourseService;
import com.example.StudentCrud.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public String viewCoursePage(Model model) {
        List<Course> listCourses = courseService.listAll();
        model.addAttribute("listCourses", listCourses);
        return "course";
    }

    @GetMapping("/new")
    public String showNewCourseForm(Model model) {
        Course course = new Course();
        List<Instructor> instructors = instructorService.listAll();

        model.addAttribute("course", course);
        model.addAttribute("instructors", instructors);

        return "new_course";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course, RedirectAttributes redirectAttributes) {
        try {
            courseService.save(course);
            redirectAttributes.addFlashAttribute("message", "Course saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to save course: " + e.getMessage());
        }
        return "redirect:/courses";
    }



    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable(name = "id") long id, RedirectAttributes redirectAttributes) {
        try {
            courseService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Course deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete course: " + e.getMessage());
        }
        return "redirect:/courses";
    }
    @GetMapping("/edit/{id}")
    public String showEditCoursePage(@PathVariable(name = "id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Course course = courseService.get(id);
            List<Instructor> instructors = instructorService.listAll();
            model.addAttribute("course", course);
            model.addAttribute("instructors", instructors);
            return "new_course";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to find course: " + e.getMessage());
            return "redirect:/courses";
        }

    }
}
