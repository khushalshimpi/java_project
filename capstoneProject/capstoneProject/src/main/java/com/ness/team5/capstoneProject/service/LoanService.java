package com.ness.team5.capstoneProject.service;



import com.ness.team5.capstoneProject.dto.LoanDTO;
import com.ness.team5.capstoneProject.entity.Loan;
import org.springframework.http.HttpStatus;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Service Layer for LoanService
 * @author Vedant
 */

public interface LoanService {
    void PostLoan(List<Loan> loan) throws IllegalStateException, URISyntaxException;
    Optional<Loan> getLoan(String id) throws IllegalStateException;
    String getStatus(String id) throws IllegalStateException;
     Loan updateLoan(String id, LoanDTO loanDTO) throws IllegalStateException;
     HttpStatus deleteLoan(String id) throws IllegalStateException;

}
