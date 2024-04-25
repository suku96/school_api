package com.demo.school.api.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="fee")
@Builder
public class Fee {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private Double tuitionFee;

    private Double booksFee;

    private Double busFee;

    private Double hostelFee;

    private Double pendingFee;
   @OneToOne(fetch=FetchType.LAZY )
    private Student student;

}
