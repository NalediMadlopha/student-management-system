package com.sms.app.service.impl;

import com.sms.app.exception.ResourceNotFoundException;
import com.sms.app.model.Student;
import com.sms.app.repository.StudentRepository;
import com.sms.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(String studentNumber, Student student) {
        Student existingStudent = studentRepository.findById(studentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentNumber", studentNumber));

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        return studentRepository.save(existingStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(String studentNumber) {
        return studentRepository.findById(studentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentNumber", studentNumber));
    }

    @Override
    public void deleteStudent(String studentNumber) {
        Student existingStudent = studentRepository.findById(studentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentNumber", studentNumber));
        studentRepository.delete(existingStudent);
    }

}
