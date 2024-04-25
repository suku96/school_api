package com.demo.school.api.service;

import com.demo.school.api.exception.ResourceNotFoundException;
import com.demo.school.api.model.Mentor;
import com.demo.school.api.repository.MentorRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MentorService {
    private MentorRepository mentorRepository;
    public void create(Mentor mentor){
        mentorRepository.save(mentor);
    }
    public List<Mentor> getAll(){
        return mentorRepository.findAll();
    }
    public Mentor getById(Long id){
        return mentorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Mentor","id",id));
    }

    public Mentor update(Long id,Mentor mentor){
        Mentor existingMentor = mentorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor", "id", id));
          existingMentor=Mentor.builder().name(existingMentor.getName())
                  .students(existingMentor.getStudents()).build();
           mentorRepository.save(existingMentor);
           return existingMentor;
    }
    public void delete(Long id){
        mentorRepository.delete(mentorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Mentor","id",id)));
    }

}
