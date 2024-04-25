package com.demo.school.api.service;

import com.demo.school.api.exception.ResourceNotFoundException;
import com.demo.school.api.model.Student;
import com.demo.school.api.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository repository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldStudentSavedToRepository() {
        //given
        Student student=new Student(null,"sukumar",17,(long)66060060,null,null);
        Student savedStudent=new Student((long)1,"sukumar",17,(long)66060060,null,null);
        //mock the call
        Mockito.when(repository.save(student)).thenReturn(savedStudent);
        //when
        Student returedStudent =studentService.save(student);
        //then
        assertEquals(student.getName(),savedStudent.getName());
        assertEquals(student.getAge(),savedStudent.getAge());
        assertEquals(student.getMentor(),savedStudent.getMentor());

        Mockito.verify(repository,Mockito.times(1)).save(student);

    }
    @Test
    public void shouldGetAllStudents(){
        //given
        List<Student> students=new ArrayList<>();
        students.add(new Student((long)1,"sukumar",17,(long)66060060,null,null));
        students.add(new Student((long)2,"qqqqqq",18,(long)544543435,null,null));
        //mock the call
        Mockito.when(repository.findAll()).thenReturn(students);
        //when
        List<Student> returnedStudents = studentService.getAll();
        //then
        assertEquals(returnedStudents.size(),students.size());

        Mockito.verify(repository,Mockito.times(1)).findAll();

    }
    @Test
    public void shouldGetStudentById(){
        //given
        Student student=new Student((long)1,"sukumar",17,(long)66060060,null,null);
        //mock the call
        Mockito.when(repository.findById(student.getId())).thenReturn(Optional.of(student));
        //when
        Student returedStudent=studentService.getbyid(student.getId());
        //then
        assertEquals(returedStudent.getName(),student.getName());
        Mockito.verify(repository,Mockito.times(1)).findById(student.getId());
    }
    @Test
    public void shouldThrowresourceNotFoundException_WhenIdIsInvalid(){
        //given
        Student student=new Student((long)1,"sukumar",17,(long)66060060,null,null);
        //mock the call
        Mockito.when(repository.findById((long)2)).thenReturn(Optional.of(student));
        //when
        assertThrows(ResourceNotFoundException.class,()-> studentService.getbyid(student.getId()));
    }
}