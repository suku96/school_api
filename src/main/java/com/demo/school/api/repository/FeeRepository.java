package com.demo.school.api.repository;

import com.demo.school.api.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<Fee,Long> {
    
}
