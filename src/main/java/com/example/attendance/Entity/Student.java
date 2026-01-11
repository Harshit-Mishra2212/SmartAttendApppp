package com.example.attendance.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;


@Document("students")
@Data
public class Student {
    @Id
    private ObjectId id;
    @NonNull
    private String name;
    @NonNull
    private String username;
    @NonNull
    private long rollNo;
    private String email;
    private Subject[] subjects;
    private Map<Subject, int[]> attendance;
    @NonNull
    private String password;
    @NonNull
    private String classs;
}
