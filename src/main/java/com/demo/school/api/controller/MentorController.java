package com.demo.school.api.controller;

import com.demo.school.api.model.Mentor;
import com.demo.school.api.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mentor")
public class MentorController {
    @Autowired
    MentorService mentorService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMentor(@RequestBody Mentor mentor){
        mentorService.create(mentor);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Mentor> getAllMentors(){
        return mentorService.getAll();
    }
    @GetMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public Mentor getMentorById(@PathVariable("id") Long id){
       return mentorService.getById(id);
    }
    @PutMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public Mentor updateMentor(@PathVariable("id") Long id,@RequestBody Mentor mentor){
        return mentorService.update(id,mentor);
    }

    @DeleteMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMentor(@PathVariable("id") Long id){
         mentorService.delete(id);
    }
}
