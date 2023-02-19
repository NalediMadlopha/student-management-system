package com.sms.app.controller;

import com.sms.app.model.Student;
import com.sms.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("{studentNumber}")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentNumber") String studentNumber, @RequestBody Student student) {
        return new ResponseEntity<>(studentService.updateStudent(studentNumber, student), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{studentNumber}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentNumber") String studentNumber) {
        return new ResponseEntity<>(studentService.getStudentById(studentNumber), HttpStatus.OK);
    }

    @DeleteMapping("{studentNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentNumber") String studentNumber) {
        studentService.deleteStudent(studentNumber);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

}
