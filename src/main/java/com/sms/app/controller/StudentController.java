package com.sms.app.controller;

import com.sms.app.model.Student;
import com.sms.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @PostMapping
//    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
//        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
//    }
//
//    @PutMapping("{STUDENT_NUMBER}")
//    public ResponseEntity<Student> updateStudent(@PathVariable("STUDENT_NUMBER") String STUDENT_NUMBER, @RequestBody Student student) {
//        return new ResponseEntity<>(studentService.updateStudent(STUDENT_NUMBER, student), HttpStatus.CREATED);
//    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/student/create")
    public String createStudent(Model model) {
        Student student = new Student();

        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/student")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }



//    @GetMapping("{STUDENT_NUMBER}")
//    public ResponseEntity<Student> getStudentById(@PathVariable("STUDENT_NUMBER") String STUDENT_NUMBER) {
//        return new ResponseEntity<>(studentService.getStudentById(STUDENT_NUMBER), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{STUDENT_NUMBER}")
//    public ResponseEntity<String> deleteStudent(@PathVariable("STUDENT_NUMBER") String STUDENT_NUMBER) {
//        studentService.deleteStudent(STUDENT_NUMBER);
//        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
//    }

}
