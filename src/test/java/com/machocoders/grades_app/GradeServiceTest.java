package com.machocoders.grades_app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.machocoders.grades_app.repository.GradeRepository;
import com.machocoders.grades_app.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesTest() {
        // Arrange: Mock the data needed to carry out the test.
        List<Grade> mockGrades = List.of(
                new Grade("Harry", "Magic", "C-"),
                new Grade("Ronnie", "Potions", "A"));
        when(gradeRepository.getGrades()).thenReturn(mockGrades);

        // Act: Call the method that you want to test
        List<Grade> result = gradeService.getGrades();

        // Assert: Check if the method is behaving correctly
        assertEquals("Harry", result.get(0).getName());
        assertEquals("Potions", result.get(1).getSubject());

    }

    @Test
    public void getGradeIndexTest() {
        // Arrange
        List<Grade> mockGrades = List.of(
                new Grade("Harry", "Magic", "C-"));
        when(gradeRepository.getGrades()).thenReturn(mockGrades);
        when(gradeRepository.getGrade(0)).thenReturn(mockGrades.get(0));

        // Act
        Grade grade = gradeService.getGrade(0);
        String validId = grade.getId();
        String invalidId = "1234";
        int found = gradeService.getGradeIndex(validId);
        int notFound = gradeService.getGradeIndex(invalidId);

        // Assert
        assertEquals(0, found);
        assertEquals(Constants.NOT_FOUND, notFound);

    }

    @Test
    public void getGradeByIdTest() {
        List<Grade> mockGrades = List.of(
                new Grade("Harry", "Magic", "C-"));
        when(gradeRepository.getGrades()).thenReturn(mockGrades);
        when(gradeRepository.getGrade(0)).thenReturn(mockGrades.get(0));

        Grade grade = gradeService.getGrade(0);
        String validId = grade.getId();
        String invalidId = "1234";

        Grade found = gradeService.getGradeById(validId);
        Grade notFound = gradeService.getGradeById(invalidId);

        assertEquals("Harry", found.getName());
        assertEquals(null, notFound.getName());

    }

    @Test
    public void addGradeTest() {
        List<Grade> mockGrades = List.of(
                new Grade("Harry", "Magic", "C-"));
        when(gradeRepository.getGrades()).thenReturn(mockGrades);
        when(gradeRepository.getGrade(0)).thenReturn(mockGrades.get(0));

        Grade newGrade = new Grade("Harry", "Magic", "C-");
        gradeService.submitGrade(newGrade);
        verify(gradeRepository).addGrade(newGrade);
    }

    @Test
    public void updateGradeTest() {
        List<Grade> mockGrades = List.of(
                new Grade("Harry", "Magic", "C-"));
        when(gradeRepository.getGrades()).thenReturn(mockGrades);
        when(gradeRepository.getGrade(0)).thenReturn(mockGrades.get(0));

        Grade grade = gradeService.getGrade(0);
        grade.setScore("A+");
        gradeService.submitGrade(grade);
        verify(gradeRepository).updateGrade(0, grade);
    }
}
