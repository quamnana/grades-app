package com.machocoders.grades_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machocoders.grades_app.Constants;
import com.machocoders.grades_app.Grade;
import com.machocoders.grades_app.repository.GradeRepository;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;

    public Grade getGrade(int index) {
        return gradeRepository.getGrade(index);
    }

    public void addGrade(Grade grade) {
        gradeRepository.addGrade(grade);
    }

    public void updateGrade(int index, Grade grade) {
        gradeRepository.updateGrade(index, grade);
    }

    public List<Grade> getGrades() {
        return gradeRepository.getGrades();
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < getGrades().size(); i++) {
            if (getGrade(i).getId().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;
    }

    public Grade getGradeById(String id) {
        int index = getGradeIndex(id);
        return index == Constants.NOT_FOUND ? new Grade() : getGrade(index);
    }

    public void submitGrade(Grade grade) {
        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            addGrade(grade);
        } else {
            updateGrade(index, grade);
        }
    }

}
