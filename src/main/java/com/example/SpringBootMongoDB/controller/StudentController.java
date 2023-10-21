package com.example.SpringBootMongoDB.controller;

import com.example.SpringBootMongoDB.DTO.Student;
import com.example.SpringBootMongoDB.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody String jsonInput) {
        return studentService.updateStudent(id, jsonInput);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }
}
