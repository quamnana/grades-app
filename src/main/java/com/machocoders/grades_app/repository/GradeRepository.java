package com.machocoders.grades_app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.machocoders.grades_app.Grade;

@Repository
public class GradeRepository {
    private List<Grade> studentGrades = new ArrayList<>();

    public Grade getGrade(int index) {
        return studentGrades.get(index);
    }

    public void addGrade(Grade grade) {
        studentGrades.add(grade);
    }

    public void updateGrade(int index, Grade grade) {
        studentGrades.set(index, grade);
    }

    public List<Grade> getGrades() {
        return studentGrades;
    }

}
