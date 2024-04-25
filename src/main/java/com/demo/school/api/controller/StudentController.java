package com.demo.school.api.controller;

import com.demo.school.api.model.Student;
import com.demo.school.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")

public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student){
        studentService.save(student);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(){
        return studentService.getAll();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable("id") Long id){
        return studentService.getbyid(id);
    }

    @GetMapping("{mobile_number}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentByName(@PathVariable("mobile_number") Long number){
        return studentService.getbyname(number);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@PathVariable("id") Long id,@RequestBody Student student){
        return studentService.update(id,student);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("id") Long id){
       studentService.delete(id);
    }


}
