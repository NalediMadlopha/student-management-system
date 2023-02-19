package com.sms.app.service;

import com.sms.app.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student updateStudent(String studentNumber, Student student);

    List<Student> getAllStudents();

    Student getStudentById(String studentNumber);

    void deleteStudent(String studentNumber);

}
