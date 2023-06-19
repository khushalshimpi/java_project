package com.MotarInsurance.service.impl;


import com.MotarInsurance.entity.MotorInsurance;
import com.MotarInsurance.enums.InsurancePackage;
import com.MotarInsurance.exception.ResourceNotFoundException;
import com.MotarInsurance.repository.MotorInsuranceRepository;
import com.MotarInsurance.service.MotorInsuranceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MotorInsuranceServiceImpl implements MotorInsuranceService {
    @Autowired
    MotorInsuranceRepository motorinsuranceRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(MotorInsuranceServiceImpl.class);




    @Override
    public ResponseEntity<MotorInsurance> create(MotorInsurance motorinsurance) throws URISyntaxException {
        String insuranceId = UUID.randomUUID().toString();
        motorinsurance.setInsuranceId(insuranceId);
        RestTemplate restTemplate = new RestTemplate();
        String scheme = "http";
        String host = "localhost";
        int port = 8083;
        String path = "/customer/";

        String customerId = motorinsurance.getCustomerId();
        URI url = new URI(scheme, null, host, port, path + customerId, null, null);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        HttpStatus status = (HttpStatus) response.getStatusCode();

        if (status.is2xxSuccessful()) {
            MotorInsurance createdInsurance = motorinsuranceRepository.save(motorinsurance);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInsurance);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }






    @Override
    public List<MotorInsurance> getAll() {
        return motorinsuranceRepository.findAll();
    }

    @Override
    public MotorInsurance get(String insuranceId) throws ResourceNotFoundException {

        MotorInsurance motorinsurance = motorinsuranceRepository.findById(insuranceId).orElseThrow(() -> new ResourceNotFoundException("Insurance with given id not found"));
        return motorinsurance;
    }

    @Override
    public MotorInsurance update(String insuranceId, MotorInsurance motorinsurance) throws URISyntaxException {

        MotorInsurance motorinsurance1 = this.motorinsuranceRepository.findById(insuranceId).orElseThrow(() -> new ResourceNotFoundException("Insurance with given id not found"));

        motorinsurance1.setMotarName(motorinsurance.getMotarName());
        motorinsurance1.setVehicleNumber(motorinsurance.getVehicleNumber());
        motorinsurance1.setYear(motorinsurance.getYear());
        return motorinsuranceRepository.save(motorinsurance1);

    }

    @Override
    public void delete(String insuranceId) {

        MotorInsurance motorinsurance = motorinsuranceRepository.findById(insuranceId).orElseThrow(() -> new ResourceNotFoundException("Insurance with given id not found"));

        boolean InsDel = motorinsuranceRepository.existsById(insuranceId);
        if(!InsDel){
            throw new IllegalStateException("Insurance deleted");
        }

        motorinsuranceRepository.deleteById(insuranceId);
    }

    @Override
    public MotorInsurance chooseInsurancePackage(String insuranceId, String insurancePackage) throws ResourceNotFoundException{

        MotorInsurance motorinsurance = motorinsuranceRepository.findById(insuranceId).orElseThrow(() -> new ResourceNotFoundException("Insurance with given id not found"));
        InsurancePackage chosenPackage =InsurancePackage.valueOf(insurancePackage);

        switch (chosenPackage) {
            case PLATINUM:
            case GOLD:
            case SILVER:
                motorinsurance.setInsuranceId(chosenPackage.toString());
                motorinsurance.setInsurancePackage(chosenPackage.toString());
                break;
            default:
                throw new IllegalArgumentException("Invalid insurance package: " + insurancePackage);
        }

        return motorinsuranceRepository.save(motorinsurance);
    }

    @Override
    public String getPackageStatus(String insuranceId) throws ResourceNotFoundException{

        MotorInsurance motorinsurance = motorinsuranceRepository.findById(insuranceId).orElseThrow(() -> new ResourceNotFoundException("Insurance with given id not found"));
        String packageStatus = motorinsurance.getInsurancePackage();

        if (packageStatus == null) {
            packageStatus = "Insurance package not selected";
        }
        return packageStatus;
    }
}
