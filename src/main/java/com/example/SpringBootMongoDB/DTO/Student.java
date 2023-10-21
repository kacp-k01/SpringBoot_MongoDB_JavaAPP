package com.example.SpringBootMongoDB.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Value
@Jacksonized
@Builder
@Document(collection = "kacperDB")
public class Student {

    @Id
    String id;
    String firstName;
    String lastName;
    @Indexed(unique = true)
    String email;
    String gender;
    String country;
    List<String> favouriteSubjects;
    BigDecimal totalSpentInBooks;
    LocalDateTime created;
}
