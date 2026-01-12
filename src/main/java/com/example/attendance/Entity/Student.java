package com.example.attendance.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Document("students")
@Data
public class Student {
    @Id
    private ObjectId id;
    @NonNull
    private String name;
    @Indexed(unique = true)
    private String username;
    @NonNull
    private long rollNo;
    private String email;
    private List<String> subjects;
    private Map<String, List<Integer>> attendance;
    private Map<String, List<Integer>> absent;
    @NonNull
    private String password;
    @NonNull
    private String classs;
}
