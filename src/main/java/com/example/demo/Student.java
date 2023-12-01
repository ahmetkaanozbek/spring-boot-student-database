package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @NotBlank(message = "Student name mustn't be blank.")
    private String studentName;
    @NotBlank(message = "Student class mustn't be blank.")
    private String studentClass;

    public Student() {
        //Default constructor for JPA
    }
    public Student(String studentName, String studentClass) {
        this.studentName = studentName;
        this.studentClass = studentClass;
    }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getStudentId() { return studentId; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getStudentName() { return studentName; }
    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }
    public String getStudentClass() { return studentClass; }
}
