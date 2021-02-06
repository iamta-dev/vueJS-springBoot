package com.okta.springbootvue.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import com.okta.springbootvue.entity.test.*;
import com.okta.springbootvue.repository.test.*;
import java.util.Collection;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class StudentController {

    @Autowired
    private final StudentRepository studentRepository;
 
    StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/")
    public Collection<Student> Student() {
        return studentRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/findByStudentCode/{stuCode}/")
    public Collection<Student> findByStudentCode(@PathVariable("stuCode") String stuCode) {
        // SQL LIKE
        stuCode = stuCode + "%";
        return studentRepository.findByStudentCode(stuCode);
    }

    @DeleteMapping("/deleteStudent/{id}/")
    public String deleteById(@PathVariable Long id) {
        Student student = studentRepository.findById((long)id);
        studentRepository.delete(student);
        return "Delete by id called";
    }

    @PatchMapping("/updateStudent/{id}/{stuCode}/{name}/{subject}/{tel}/")
    public String patchById(@PathVariable Long id,
    @PathVariable String stuCode,
    @PathVariable String name,
    @PathVariable String subject,
    @PathVariable String tel
    ) {
        Student student = studentRepository.findById((long)id);
        student.setStuCode((String)stuCode);
        student.setName((String)name);
        student.setSubject((String)subject);
        student.setTel((String)tel);
        studentRepository.save(student);
        return "Update by id called";
    }      

    @PostMapping("/saveStudent/{stuCode}/{name}/{subject}/{tel}/")
    public Student newStudent(Student newStudent,
    @PathVariable String stuCode,
    @PathVariable String name,
    @PathVariable String subject,
    @PathVariable String tel) {
 
    newStudent.setStuCode(stuCode);
    newStudent.setName(name);
    newStudent.setSubject(subject);
    newStudent.setTel(tel);
    return studentRepository.save(newStudent);
    }
}