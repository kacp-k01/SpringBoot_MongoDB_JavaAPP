package com.example.SpringBootMongoDB;

import com.example.SpringBootMongoDB.DTO.Student;
import com.example.SpringBootMongoDB.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringBootMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
    }

//    test run
//
//    @Bean
//    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
//        return args -> {
//
//            Student student = Student.builder()
//                    .firstName("Thomas")
//                    .lastName("X")
//                    .email("XXXXXX1")
//                    .gender("M")
//                    .country("Poland")
//                    .favouriteSubjects(List.of("it", "math", "ppj"))
//                    .totalSpentInBooks(BigDecimal.TEN)
//                    .created(LocalDateTime.now().toString())
//                    .build();
//
//            repository.insert(student);
//            Query query = new Query();
//            query.addCriteria(Criteria.where("email").is("XXXXXX1"));
//            List<Student> students = mongoTemplate.find(query, Student.class);
//            for (Student stud : students) {
//                System.err.println(stud.toString());
//            }
//
//        };
//    }

}
