package com.MotarInsurance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "motorinsurance")
public class MotorInsurance {

    @Id
    private String customerId;
    private String insuranceId;

    private String motarName;

    private String vehicleNumber;

    private int year;

    @Column(name ="package")
    private String insurancePackage;


}



