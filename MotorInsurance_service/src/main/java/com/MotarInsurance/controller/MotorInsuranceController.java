package com.MotarInsurance.controller;


import com.MotarInsurance.entity.MotorInsurance;
import com.MotarInsurance.exception.ResourceNotFoundException;
import com.MotarInsurance.payloads.ApiResponse;
import com.MotarInsurance.payloads.ApiResponse1;
import com.MotarInsurance.service.MotorInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/motorinsurance")

public class MotorInsuranceController {

    @Autowired
    private MotorInsuranceService motorinsuranceservice;

    //swagger

    @GetMapping("/")
//    @ApiOperation("Hon=me page")
    public String home() {
        return "Welcome to the Loan API!";
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<MotorInsurance> createInsurance(@RequestBody MotorInsurance motorinsurance) {
        try {
            return motorinsuranceservice.create(motorinsurance);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    // Get all

    @GetMapping("/all")
    public ResponseEntity<List<MotorInsurance>> getMotorInsurances(){
        return ResponseEntity.ok(motorinsuranceservice.getAll());
    }

    // Get one

    @GetMapping("/{insuranceId}")
    public ResponseEntity<MotorInsurance> getMotorInsurances(@PathVariable String insuranceId){

        return ResponseEntity.status(HttpStatus.OK).body(motorinsuranceservice.get(insuranceId));

    }

    //delete

    @DeleteMapping("/{insuranceId}")
    public ApiResponse deleteMotorInsurance(@PathVariable String insuranceId)
    {

        motorinsuranceservice.delete(insuranceId);
        return new ApiResponse(" MotorInsurance is Successfully Deleted !!", true);
    }

    //update

    @PutMapping("/{insuranceId}")
    public ResponseEntity<MotorInsurance> updateMotorInsurance(@RequestBody MotorInsurance motorinsurance, @PathVariable String insuranceId) throws URISyntaxException {

        return ResponseEntity.status(HttpStatus.OK).body(motorinsuranceservice.update(insuranceId, motorinsurance));

    }

    //insurancePackage

    @PostMapping("/{insuranceId}/{insurancePackage}")
    public ResponseEntity<MotorInsurance> chooseInsurancePackage(
            @PathVariable String insuranceId,
            @PathVariable String insurancePackage
    ) throws ResourceNotFoundException, URISyntaxException {
        MotorInsurance motorinsurance = motorinsuranceservice.chooseInsurancePackage(insuranceId, insurancePackage);
        return ResponseEntity.status(HttpStatus.CREATED).body(motorinsuranceservice.create(motorinsurance).getBody());
    }

    //status

    @GetMapping("/{insuranceId}/package-status")
    public ResponseEntity<String> getPackageStatus(@PathVariable String insuranceId) throws ResourceNotFoundException {
        return ResponseEntity.ok(motorinsuranceservice.getPackageStatus(insuranceId));
    }

}
