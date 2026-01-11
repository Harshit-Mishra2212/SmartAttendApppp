package com.example.attendance.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teachers")
@Data
public class Teacher {
    @Id
    private ObjectId id;
    private String name;
    private String department;
    private Subject[] subjects;
}
