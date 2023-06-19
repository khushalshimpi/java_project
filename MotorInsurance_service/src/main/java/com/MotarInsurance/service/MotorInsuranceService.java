package com.MotarInsurance.service;

import com.MotarInsurance.entity.MotorInsurance;
import com.MotarInsurance.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;


public interface MotorInsuranceService  {
    // create

    ResponseEntity<MotorInsurance> create(MotorInsurance motorinsurance) throws URISyntaxException;


    // get all

    List<MotorInsurance> getAll();

    //get single

    MotorInsurance get(String insuranceId) throws ResourceNotFoundException;


    //update

    MotorInsurance update(String insuranceId, MotorInsurance motorinsurance) throws URISyntaxException;

    //delete

    void delete(String insuranceId);

    //package

    MotorInsurance chooseInsurancePackage(String insuranceId, String insurancePackage) throws ResourceNotFoundException;

    String getPackageStatus(String insuranceId) throws ResourceNotFoundException;


}


