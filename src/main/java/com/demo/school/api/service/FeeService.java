package com.demo.school.api.service;

import com.demo.school.api.exception.ResourceNotFoundException;
import com.demo.school.api.model.Fee;
import com.demo.school.api.repository.FeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
    @Service
    @AllArgsConstructor
    public class FeeService {
        private FeeRepository feeRepository;
        public List<Fee> getAll(){
            return feeRepository.findAll();
        }
        public Fee getById(Long id){
            return feeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Fee","Id",id));
        }
        public Fee alterByid(Long id,Fee fee){
            Fee excistingFee = feeRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Fee", "Id", id));
            excistingFee=Fee.builder().tuitionFee(fee.getTuitionFee()).booksFee(fee.getBooksFee()).busFee(fee.getBusFee())
                    .hostelFee(fee.getHostelFee()).pendingFee(fee.getPendingFee()).student(fee.getStudent()).build();
            feeRepository.save(excistingFee);
            return excistingFee;
        }
        public void deleteByid(Long id){
            feeRepository.delete(feeRepository.findById(id).orElseThrow(()
                    -> new ResourceNotFoundException("Fee","Id",id)));
        }


    
}
