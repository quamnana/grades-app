package com.machocoders.grades_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.machocoders.grades_app.Grade;
import com.machocoders.grades_app.service.GradeService;

import jakarta.validation.Valid;

@Controller
public class GradeController {
    @Autowired
    GradeService gradeService;

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getGrades());
        return "grades";
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result) {
        if (result.hasErrors())
            return "form";
        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }

    public int getGradeIndex(String id) {
        return gradeService.getGradeIndex(id);
    }

}
