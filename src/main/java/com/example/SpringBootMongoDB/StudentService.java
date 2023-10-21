package com.example.SpringBootMongoDB;

import com.example.SpringBootMongoDB.DTO.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepositorys;

    public List<Student> getAllStudents(){
        return studentRepositorys.findAll();
    }
}
