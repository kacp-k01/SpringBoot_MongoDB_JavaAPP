package com.example.SpringBootMongoDB;

import com.example.SpringBootMongoDB.DTO.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class SpringBootMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Student student = Student.builder()
                    .firstName("Fifo")
                    .lastName("kajto")
                    .email("XXXXXX")
                    .gender("M")
                    .country("Kajtoland")
                    .favouriteSubjects(List.of("it", "math", "ppj"))
                    .totalSpentInBooks(BigDecimal.TEN)
                    .created(LocalDateTime.now())
                    .build();

            Student student1 = Student.builder()
                    .firstName("KajtoBeta")
                    .lastName("kajto")
                    .email("XXXXXX1")
                    .gender("M")
                    .country("Kajtoland")
                    .favouriteSubjects(List.of("it", "math", "ppj"))
                    .totalSpentInBooks(BigDecimal.TEN)
                    .created(LocalDateTime.now())
                    .build();

            repository.insert(student);
            repository.insert(student1);

            Query query = new Query();

            query.addCriteria(Criteria.where("email").is("XXXXXX1"));

            List<Student> students = mongoTemplate.find(query, Student.class);

            for (Student stud : students) {
                System.err.println(stud.toString());
            }

        };
    }

}
