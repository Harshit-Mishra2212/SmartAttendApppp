package com.example.attendance.Repository;

import com.example.attendance.Entity.Subject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface SubjectRepository extends MongoRepository<Subject , ObjectId> {

}
