package com.MotarInsurance.repository;

import com.MotarInsurance.entity.MotorInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MotorInsuranceRepository extends JpaRepository<MotorInsurance, String> {

}
