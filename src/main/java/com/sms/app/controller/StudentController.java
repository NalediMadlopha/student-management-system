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

    @PutMapping("{STUDENT_NUMBER}")
    public ResponseEntity<Student> updateStudent(@PathVariable("STUDENT_NUMBER") String STUDENT_NUMBER, @RequestBody Student student) {
        return new ResponseEntity<>(studentService.updateStudent(STUDENT_NUMBER, student), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{STUDENT_NUMBER}")
    public ResponseEntity<Student> getStudentById(@PathVariable("STUDENT_NUMBER") String STUDENT_NUMBER) {
        return new ResponseEntity<>(studentService.getStudentById(STUDENT_NUMBER), HttpStatus.OK);
    }

    @DeleteMapping("{STUDENT_NUMBER}")
    public ResponseEntity<String> deleteStudent(@PathVariable("STUDENT_NUMBER") String STUDENT_NUMBER) {
        studentService.deleteStudent(STUDENT_NUMBER);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

}
