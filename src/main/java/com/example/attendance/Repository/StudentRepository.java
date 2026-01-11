package com.example.attendance.Repository;

import com.example.attendance.Entity.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface StudentRepository extends MongoRepository<Student, ObjectId> {
    List<Student> findByUsername(String username);
}
