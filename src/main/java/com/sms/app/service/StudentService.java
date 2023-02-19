package com.sms.app.service;

import com.sms.app.model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    Student updateStudent(String studentNumber, Student student);

    List<Student> getAllStudents();

    Student getStudentById(String studentNumber);

    void deleteStudent(String studentNumber);

}
