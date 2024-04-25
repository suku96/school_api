package com.demo.school.api.service;

import com.demo.school.api.exception.ResourceNotFoundException;
import com.demo.school.api.model.Student;
import com.demo.school.api.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public Student save(Student student){
        return studentRepository.save(student);
    }
    public List<Student> getAll(){
        return studentRepository.findAll();
    }
    public Student getbyid(Long id){
        return studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student","id",id));
//         Optional<Student> optionalStudent = studentRepository.findById(id);
//          if(optionalStudent.isPresent())
//              return optionalStudent.get();
//          else throw new ResourceNotFoundException("Student","id",id);
    }
    public Student getbyname(Long number){
        return studentRepository.findBymobileNumber(number)
                .orElseThrow(()->new ResourceNotFoundException("Student","mobilenumber",number));
    }
    public Student update(Long id,Student student){
       Student existingstudent =studentRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Student","id",id));
           existingstudent=Student.builder().name(student.getName()).age(student.getAge()).mobileNumber(student.getMobileNumber()).
                   mentor(student.getMentor()).fees(student.getFees()).build();
           studentRepository.save(existingstudent);
           return existingstudent;

    }
    public void delete(Long id){
       studentRepository.delete(studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student","id",id)));
    }

}
