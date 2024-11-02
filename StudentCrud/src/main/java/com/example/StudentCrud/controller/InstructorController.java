package com.example.StudentCrud.controller;

import com.example.StudentCrud.domain.Instructor;
import com.example.StudentCrud.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public String listInstructors(Model model) {
        List<Instructor> listInstructors = instructorService.listAll();
        model.addAttribute("listInstructors", listInstructors);
        return "instructor/index"; // Assuming your template is at src/main/resources/templates/instructor/index.html
    }

    @GetMapping("/new")
    public String addInstructorForm(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor/new"; // Assuming your template is at src/main/resources/templates/instructor/new.html
    }

    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor, RedirectAttributes redirectAttributes) {
        try {
            instructorService.save(instructor);
            redirectAttributes.addFlashAttribute("message", "Instructor saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to save instructor: " + e.getMessage());
        }
        return "redirect:/instructors";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editInstructor(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("instructor/new"); // Assuming the same form is used for editing
        Instructor instructor = instructorService.get(id);
        mav.addObject("instructor", instructor);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable(name = "id") long id, RedirectAttributes redirectAttributes) {
        try {
            instructorService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Instructor deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete instructor: " + e.getMessage());
        }
        return "redirect:/instructors";
    }


}
