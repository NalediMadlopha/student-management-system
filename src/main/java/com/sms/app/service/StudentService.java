package com.sms.app.service;

import com.sms.app.model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    Student updateStudent(String STUDENT_NUMBER, Student student);

    List<Student> getAllStudents();

    Student getStudentById(String STUDENT_NUMBER);

    void deleteStudent(String STUDENT_NUMBER);

}
