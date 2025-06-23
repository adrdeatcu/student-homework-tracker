package com.adrian.studentapp.controller;

import com.adrian.studentapp.model.Student;
import com.adrian.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository)
    {
        this.studentRepository=studentRepository;
    }

    @GetMapping("")
    public List<Student> getAllStudents()
    {
       return studentRepository.findAll();
    }

    @PostMapping("")
    public Student addStudent(@RequestBody Student student)
    {
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id)
    {
        return studentRepository.findById(id).orElse(null);

    }
}
