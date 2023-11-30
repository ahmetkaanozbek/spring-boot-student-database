package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepo.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public Optional<Student> updateStudent(Student updatedStudent, Integer id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setStudentClass(updatedStudent.getStudentClass());
            student.setStudentName(updatedStudent.getStudentName());
            return Optional.of(studentRepo.save(student));
        }
        return Optional.empty();
    }

    public Optional<Student> deleteStudent(Integer id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepo.deleteById(id);
            return optionalStudent;
        }
        return Optional.empty();
    }
}
