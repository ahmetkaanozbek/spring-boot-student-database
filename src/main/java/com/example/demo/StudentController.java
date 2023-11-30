package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/all")
    List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value="/{id}")
    public Optional<Student> getStudent(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @PostMapping(value="/new_student")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping(value="/{id}")
    public Optional<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
        return studentService.updateStudent(student, id);
    }

    @DeleteMapping(value="/{id}")
    public Optional<Student> deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }
}
