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

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updateStudent)
    {
        Student existingStudent=studentRepository.findById(id).orElse(null);
        if(existingStudent != null)
        {
            existingStudent.setName(updateStudent.getName());
            existingStudent.setAge(updateStudent.getAge());
            existingStudent.setDepartment(updateStudent.getDepartment());

            studentRepository.save(existingStudent);
        }
        else {
            return null;
        }
        return existingStudent;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id)
    {
        Student existingStudent=studentRepository.findById(id).orElse(null);
        if(existingStudent != null)
        {
            studentRepository.deleteById(id);
        }
    }
}
