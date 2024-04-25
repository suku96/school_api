package com.demo.school.api.reportservice;

import com.demo.school.api.model.Fee;
import com.demo.school.api.reportdto.FeeReportDto;
import com.demo.school.api.repository.FeeRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReportService {
    private FeeRepository feeRepository;
    public List<FeeReportDto> feeReportGeneration() throws FileNotFoundException, JRException {
        List<Fee> fees=feeRepository.findAll();
        List<FeeReportDto> feeDto = fees.stream().map((fee) -> maptoDto(fee)).toList();

        String path="C:\\Suku\\jasper";
        File file= ResourceUtils.getFile("src/main/resources/fee.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(feeDto);
        Map<String,Object > parameter=new HashMap<>();
        parameter.put("createdby ","admin");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameter,dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\fee1.pdf");
        return feeDto;

    }
    private FeeReportDto maptoDto(Fee fee) {
        FeeReportDto feeReportDto;
        return FeeReportDto.builder().id(fee.getId()).tuitionFee(fee.getTuitionFee()).
                booksFee(fee.getBooksFee()).busFee(fee.getBusFee()).hostelFee(fee.getHostelFee())
                .pendingFee(fee.getPendingFee()).build();

    }
    public List<FeeReportDto> feeReport1() throws FileNotFoundException, JRException {
        String path="C:\\Suku\\jasper";
        List<Fee> fees=feeRepository.findAll();
        List<FeeReportDto> feeDto = fees.stream().map((fee) -> maptoDto(fee)).toList();
        JRBeanCollectionDataSource jrBeanCollectionDataSource=new JRBeanCollectionDataSource(feeDto);
        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/fee.jrxml"));
        Map<String,Object > parameter=new HashMap<>();
        parameter.put("created by ","admin");
        JasperPrint jasperprint = JasperFillManager.fillReport(compileReport, parameter, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperprint,"invoice.pdf");
        return feeDto;
    }

}
