package com.sms.app.service.impl;

import com.sms.app.exception.ResourceNotFoundException;
import com.sms.app.model.Student;
import com.sms.app.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class StudentServiceImplTest {

    private final static String STUDENT_NUMBER = "stu1234567";
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository mockStudentRepository;
    @Mock
    private Student mockStudent;

    @BeforeEach
    void setUp() {
        openMocks(this);
        mockStudent = new Student(STUDENT_NUMBER, "John", "Doe", "john.doe@email.com");

        studentService = new StudentServiceImpl(mockStudentRepository);
    }

    @Test
    void saveStudent_shouldInvokeStudentRepositorySave() {
        studentService.saveStudent(mockStudent);

        verify(mockStudentRepository).save(mockStudent);
    }

    @Test
    void updateStudent_throwResourceNotFoundException_ifStudentNotFound() {
        when(mockStudentRepository.findById(STUDENT_NUMBER))
                .thenThrow(new ResourceNotFoundException("Student", "studentNumber", STUDENT_NUMBER));

        assertThrows(ResourceNotFoundException.class,
                () -> studentService.updateStudent(STUDENT_NUMBER, mockStudent));
    }

    @Test
    void updateStudent_shouldInvokeStudentRepositorySave_ifStudentExists() {
        when(mockStudentRepository.findById(STUDENT_NUMBER))
                .thenReturn(Optional.of(mockStudent));

        studentService.updateStudent(STUDENT_NUMBER, mockStudent);

        verify(mockStudentRepository).save(mockStudent);
    }

    @Test
    void getAllStudents_shouldInvokeStudentRepository_findAll() {
        studentService.getAllStudents();

        verify(mockStudentRepository).findAll();
    }

    @Test
    void getStudentById_throwResourceNotFoundException_ifStudentNotFound() {
        String STUDENT_NUMBER = "stu1234567";

        when(mockStudentRepository.findById(STUDENT_NUMBER))
                .thenThrow(new ResourceNotFoundException("Student", "studentNumber", STUDENT_NUMBER));

        assertThrows(ResourceNotFoundException.class,
                () -> studentService.getStudentById(STUDENT_NUMBER));
    }

    @Test
    void getStudentById_returnStudent_ifStudentExists() {
        when(mockStudentRepository.findById(STUDENT_NUMBER)).thenReturn(Optional.of(mockStudent));

        assertEquals(mockStudent,  studentService.getStudentById(STUDENT_NUMBER));
    }

    @Test
    void deleteStudent_throwResourceNotFoundException_ifStudentNotFound() {
        when(mockStudentRepository.findById(STUDENT_NUMBER))
                .thenThrow(new ResourceNotFoundException("Student", "studentNumber", STUDENT_NUMBER));

        assertThrows(ResourceNotFoundException.class,
                () -> studentService.deleteStudent(STUDENT_NUMBER));
    }

    @Test
    void deleteStudent_shouldInvokeStudentRepositoryDelete_ifStudentExists() {
        when(mockStudentRepository.findById(STUDENT_NUMBER))
                .thenReturn(Optional.of(mockStudent));

        studentService.deleteStudent(STUDENT_NUMBER);

        verify(mockStudentRepository).delete(mockStudent);
    }

}