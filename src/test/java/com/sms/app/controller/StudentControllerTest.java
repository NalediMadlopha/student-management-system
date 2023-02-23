package com.sms.app.controller;

import com.sms.app.model.Student;
import com.sms.app.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class StudentControllerTest {

    private final static String STUDENT_NUMBER = "stu1234567";
    private StudentController  studentController;

    @Mock
    private StudentService mockStudentService;
    @Mock
    private Student mockStudent;

    @BeforeEach
    void setUp() {
        openMocks(this);

        studentController = new StudentController(mockStudentService);
    }

    @Test
    void saveStudent_shouldInvokeStudentService_saveStudent() {
        studentController.saveStudent(mockStudent);

        verify(mockStudentService).saveStudent(mockStudent);
    }

    @Test
    void saveStudent_shouldReturnExpected_httpStatus() {
        ResponseEntity<Student> responseEntity = studentController.saveStudent(mockStudent);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void saveStudent_returnResponseWithExpected_body() {
        when(mockStudentService.saveStudent(mockStudent)).thenReturn(mockStudent);

        ResponseEntity<Student> responseEntity = studentController.saveStudent(mockStudent);

        assertEquals(mockStudent, responseEntity.getBody());
    }

    @Test
    void updateStudent_shouldInvokeStudentService_updateStudent() {
        studentController.updateStudent(STUDENT_NUMBER, mockStudent);

        verify(mockStudentService).updateStudent(STUDENT_NUMBER, mockStudent);
    }

    @Test
    void updateStudent_shouldReturnExpected_httpStatus() {
        ResponseEntity<Student> responseEntity = studentController.updateStudent(STUDENT_NUMBER, mockStudent);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void updateStudent_returnResponseWithExpected_body() {
        when(mockStudentService.updateStudent(STUDENT_NUMBER, mockStudent)).thenReturn(mockStudent);

        ResponseEntity<Student> responseEntity = studentController.updateStudent(STUDENT_NUMBER, mockStudent);

        assertEquals(mockStudent, responseEntity.getBody());
    }

    @Test
    void getAllStudents_shouldInvokeStudentService_getAllStudents() {
        studentController.getAllStudents();

        verify(mockStudentService).getAllStudents();
    }

    @Test
    void getAllStudents_shouldReturn_students() {
        List<Student> students = new ArrayList<>();
        students.add(mockStudent);

        when(mockStudentService.getAllStudents()).thenReturn(students);

        assertEquals(students, studentController.getAllStudents());
        assertTrue(studentController.getAllStudents().contains(mockStudent));
    }

    @Test
    void getStudentById_shouldReturnExpected_httpStatus() {
        ResponseEntity<Student> responseEntity = studentController.getStudentById(STUDENT_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getStudentById_returnResponseWithExpected_body() {
        when(mockStudentService.getStudentById(STUDENT_NUMBER)).thenReturn(mockStudent);

        ResponseEntity<Student> responseEntity = studentController.getStudentById(STUDENT_NUMBER);

        assertEquals(mockStudent, responseEntity.getBody());
    }

    @Test
    void deleteStudent_shouldReturnExpected_httpStatus() {
        ResponseEntity<String> responseEntity = studentController.deleteStudent(STUDENT_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteStudent_shouldInvokeStudentService_deleteStudent() {
        studentController.deleteStudent(STUDENT_NUMBER);

        verify(mockStudentService).deleteStudent(STUDENT_NUMBER);
    }

    @Test
    void deleteStudent_returnResponseWithExpected_body() {
        ResponseEntity<String> responseEntity = studentController.deleteStudent(STUDENT_NUMBER);

        assertEquals("Student deleted successfully", responseEntity.getBody());
    }

}