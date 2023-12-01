package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> getStudent(@PathVariable Integer id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(student.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No valid ID.");
    }

    @PostMapping(value="/new_student")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: Student name mustn't be blank.");
        }
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student, BindingResult result, @PathVariable Integer id) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: Student name mustn't be blank.");
        }
        Optional<Student> updatedStudent = studentService.updateStudent(student, id);
        if (updatedStudent.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No valid ID.");
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        Optional<Student> student = studentService.deleteStudent(id);
        if (student.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(student.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No valid ID.");
    }
}
