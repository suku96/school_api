package com.demo.school.api.controller;

import com.demo.school.api.model.Fee;
import com.demo.school.api.reportdto.FeeReportDto;
import com.demo.school.api.reportservice.ReportService;
import com.demo.school.api.service.FeeService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/fee")
@AllArgsConstructor
public class FeeController {
    FeeService feeService;
    ReportService reportService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Fee> getAllFee(){
        return feeService.getAll();
    }
    @GetMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public Fee getFeeByid(@PathVariable("id") Long id){
        return feeService.getById(id);
    }
    @PutMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public Fee alterFeeById(@PathVariable("id") Long id,@RequestBody Fee fee){
        return feeService.alterByid(id,fee);
    }
    @DeleteMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteFeeById(@PathVariable("id ") Long id){
        feeService.deleteByid(id);
    }
    @GetMapping("/report")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FeeReportDto> feeReport() throws JRException, FileNotFoundException {
        return reportService.feeReport1();
    }

}
