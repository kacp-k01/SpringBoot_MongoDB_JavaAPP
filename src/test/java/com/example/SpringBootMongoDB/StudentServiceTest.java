package com.example.SpringBootMongoDB;

import com.example.SpringBootMongoDB.DTO.Student;
import com.example.SpringBootMongoDB.repository.StudentRepository;
import com.example.SpringBootMongoDB.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;
    @MockBean
    private StudentRepository studentRepository;

    @BeforeEach
    void setUpData() {
        // Test data set up
        List<String> subjects = new ArrayList<>();
        subjects.add("Math");
        subjects.add("Science");

        Student student = Student.builder()
                .id("1")
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .gender("Male")
                .country("USA")
                .favouriteSubjects(subjects)
                .totalSpentInBooks(new BigDecimal("100.00"))
                .created("2023-10-21T16:59:23.410697400")
                .build();

        List<Student> allStudents = new ArrayList<>();
        allStudents.add(student);

        when(studentRepository.findAll()).thenReturn(allStudents);
        when(studentRepository.findById("1")).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        studentService.createStudent(student);
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = studentService.getAllStudents();
        assertEquals(1, students.size());
    }

    @Test
    public void testCreateStudent() throws IOException, JSONException {
        Student student = studentService.getStudent("1");
        ObjectMapper objectMapper = new ObjectMapper();
        String actualStudent = objectMapper.writeValueAsString(student);
        String expectedStudent = Files.readString(Paths.get("src/test/resources/testCases/Student1.json"));
        JSONAssert.assertEquals(expectedStudent, actualStudent, JSONCompareMode.STRICT);
        Mockito.verify(studentRepository, Mockito.times(1)).save(any(Student.class));
    }

    @Test
    public void testUpdateStudent() throws IOException, JSONException {
        String update = Files.readString(Paths.get("src/test/resources/testCases/Update.json"));
        Student updatedStudent = studentService.updateStudent("1", update);
        ObjectMapper objectMapper = new ObjectMapper();
        String actualStudent = objectMapper.writeValueAsString(updatedStudent);
        String expectedStudent = Files.readString(Paths.get("src/test/resources/testCases/Student1.json"));
        JSONAssert.assertEquals(expectedStudent, actualStudent, JSONCompareMode.STRICT);
    }

    @Test
    public void testDeleteStudent() {
        studentService.deleteStudent("1");
        Mockito.verify(studentRepository, Mockito.times(1)).delete(any(Student.class));
    }
}