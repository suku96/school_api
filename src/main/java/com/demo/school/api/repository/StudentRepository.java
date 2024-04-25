package com.demo.school.api.repository;

import com.demo.school.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
       Optional<Student> findBymobileNumber(Long number);
}
