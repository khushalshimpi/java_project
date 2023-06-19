package com.MotarInsurance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.MotarInsurance.controller.MotorInsuranceController;
import com.MotarInsurance.entity.MotorInsurance;
import com.MotarInsurance.exception.ResourceNotFoundException;
import com.MotarInsurance.service.MotorInsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class MotorInsuranceServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private MotorInsuranceService motorInsuranceService;

	@InjectMocks
	private MotorInsuranceController motorInsuranceController;

	@BeforeEach
	void setUp() {

	}

	@Test
	void testGetMotorInsurances_Success() throws ResourceNotFoundException {
		// Mocking the service response
		MotorInsurance motorInsurance = new MotorInsurance();
		motorInsurance.setInsuranceId("12345");
		motorInsurance.setMotarName("Car");

		when(motorInsuranceService.get("12345")).thenReturn(motorInsurance);

		// Calling the controller method
		ResponseEntity<MotorInsurance> response = motorInsuranceController.getMotorInsurances("12345");

		// Verifying the response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(motorInsurance, response.getBody());
	}

//	@Test
//	void testGetMotorInsurances_NotFound() {
//		// Mocking the service response for a non-existing insuranceId
//		when(motorInsuranceService.get("12345")).thenThrow(new ResourceNotFoundException());
//
//		// Calling the controller method
//		try {
//			motorInsuranceController.getMotorInsurances("12345");
//		} catch (ResourceNotFoundException ex) {
//			// Verifying the exception
//			assertEquals("Insurance with given id not found", ex.getMessage());
//		}
//	}



}
