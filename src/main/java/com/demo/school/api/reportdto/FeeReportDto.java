package com.demo.school.api.reportdto;

import com.demo.school.api.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FeeReportDto {
    private Long id;
    private Double tuitionFee;

    private Double booksFee;

    private Double busFee;

    private Double hostelFee;

    private Double pendingFee;


}
