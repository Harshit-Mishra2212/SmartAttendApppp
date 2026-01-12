package com.example.attendance.Controller;

import com.example.attendance.Entity.Student;
import com.example.attendance.Entity.Subject;
import com.example.attendance.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    //add subject to student class - student controller - done
    // mark attendance - student controller - done

    @Autowired
    private StudentService studentService;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting(@RequestBody Student student)
    {
        return studentService.greeting(student);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Student student){
        return studentService.signin(student);
    }
    @PutMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Student student) // working
    {
        return studentService.signup(student);
    }
    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(@RequestBody Student student)
    {
        return studentService.deleteStudentAccount(student);
    }
    @PostMapping("/add-subject")
    public ResponseEntity<?> addSubject(@RequestBody  String username, @RequestBody List<String> subject)
    {
        List<Student> students = studentService.findbyUsername(username);
        if(!students.isEmpty())
        {
            students.getFirst().setSubjects(subject);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        else
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }
    @PutMapping("/remove-subject")
    public ResponseEntity<?> removesubject(@RequestBody String username, @RequestBody String subject)
    {
        List<Student> students = studentService.findbyUsername(username);
        if(!students.isEmpty())
        {
            students.getFirst().getSubjects().remove(subject);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        else
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }
    @PostMapping("/mark-present")
    public ResponseEntity<?> markPresent(@RequestBody String subject, @RequestBody String username)
    {
        boolean b = studentService.markAttendance(username, subject, LocalDate.now().getDayOfMonth());
        if(b)
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        else
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }
    @PostMapping("/mark-absent")
    public ResponseEntity<?> markAbsent(@RequestBody String subject, @RequestBody String username)
    {
        boolean b = studentService.markAbsent(username, subject, LocalDate.now().getDayOfMonth());
        if(b)
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        else
        {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }
    @GetMapping("/average-attendance")
    public ResponseEntity<?> averageAttendanceBySubject(@RequestBody Subject subject, @RequestBody String username)
    {
        List<Student> students = studentService.findbyUsername(username);
        double average=0.0;
        if(!students.isEmpty())
        {
            double presentDays=students.getFirst().getAttendance().size();
            double absentDays=students.getFirst().getAbsent().size();
            average=presentDays/(presentDays+absentDays);
            return new ResponseEntity<>(average, HttpStatusCode.valueOf(200));
        }
        else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }
    @GetMapping("/present-days")
    public ResponseEntity<?> getPresentDays(@RequestBody String subject, @RequestBody String username)
    {
        List<Student> students = studentService.findbyUsername(username);
        if(!students.isEmpty())
        {
            return new ResponseEntity<>(students.getFirst().getAttendance().get(subject), HttpStatusCode.valueOf(200));
        }
        else
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
    }
    @GetMapping("/absent-days")
    public ResponseEntity<?> getAbsentDays(@RequestBody String subject, @RequestBody String username)
    {
        List<Student> students = studentService.findbyUsername(username);
        if(!students.isEmpty())
        {
            return new ResponseEntity<>(students.getFirst().getAbsent().get(subject), HttpStatusCode.valueOf(200));
        }
        else
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
    }
}
