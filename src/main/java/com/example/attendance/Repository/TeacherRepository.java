package com.example.attendance.Repository;

import com.example.attendance.Entity.Teacher;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface TeacherRepository extends MongoRepository<Teacher, ObjectId> {
}
