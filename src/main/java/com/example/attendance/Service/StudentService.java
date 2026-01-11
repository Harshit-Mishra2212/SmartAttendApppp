package com.example.attendance.Service;

import com.example.attendance.Entity.Student;
import com.example.attendance.Entity.Subject;
import com.example.attendance.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public ResponseEntity<?> signup(@RequestBody Student student)
    {
        List<Student> byUsername = studentRepository.findByUsername(student.getUsername());
        if(byUsername.isEmpty())
        {
            student.setPassword(Objects.requireNonNull(passwordEncoder.encode(student.getPassword())));
            studentRepository.insert(student);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        else
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(208));
        }
    }

    public ResponseEntity<?> signin(@RequestBody Student student)
    {
        String encode = passwordEncoder.encode(student.getPassword());
        List<Student> byUsername = studentRepository.findByUsername(student.getUsername());
        if(!byUsername.isEmpty() && encode!=null && encode.equals(byUsername.getFirst().getPassword()))
        {
            return new ResponseEntity<>(byUsername.getFirst(), HttpStatusCode.valueOf(200));
        }
        else
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        }
    }

    public ResponseEntity<?> deleteStudentAccount(@RequestBody Student student)
    {
        List<Student> byUsername = studentRepository.findByUsername(student.getUsername());
        if(!byUsername.isEmpty())
        {
            studentRepository.delete(byUsername.getFirst());
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        else
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }
    //add subject to student class - student controller
    // mark attendance
}
