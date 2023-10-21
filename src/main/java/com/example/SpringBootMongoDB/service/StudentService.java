package com.example.SpringBootMongoDB.service;

import com.example.SpringBootMongoDB.DTO.Student;
import com.example.SpringBootMongoDB.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(String id, @RequestBody String jsonInput) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {
            JSONObject jsonObject = new JSONObject(jsonInput);

            Student updatedStudent = existingStudent.toBuilder()
                    .firstName(jsonObject.optString("firstName", existingStudent.getFirstName()))
                    .lastName(jsonObject.optString("lastName", existingStudent.getLastName()))
                    .email(jsonObject.optString("email", existingStudent.getEmail()))
                    .gender(jsonObject.optString("gender", existingStudent.getGender()))
                    .country(jsonObject.optString("country", existingStudent.getCountry()))
                    .favouriteSubjects(jsonObject.has("favouriteSubjects")
                            ? jsonObject.getJSONArray("favouriteSubjects")
                            .toList()
                            .stream().map(Object::toString).toList()
                            : existingStudent.getFavouriteSubjects())
                    .totalSpentInBooks(jsonObject.has("totalSpentInBooks")
                            ? jsonObject.getBigDecimal("totalSpentInBooks")
                            : existingStudent.getTotalSpentInBooks())
                    .build();

            return studentRepository.save(updatedStudent);
        } else {
            return null;
        }
    }

    public void deleteStudent(String id) {
        studentRepository.findById(id).ifPresent(studentRepository::delete);
    }

}
