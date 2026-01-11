package com.example.attendance.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subjects")
@Data
public class Subject {
    @Id
    private ObjectId id;
    private String name;
    private int noOfStudents;
    private Teacher[] teachers;
}
