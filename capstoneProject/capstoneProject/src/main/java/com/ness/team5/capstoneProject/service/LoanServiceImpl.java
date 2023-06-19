package com.ness.team5.capstoneProject.service;

import com.ness.team5.capstoneProject.dto.LoanDTO;
import com.ness.team5.capstoneProject.entity.Loan;
import com.ness.team5.capstoneProject.mapper.LoanMapper;
import com.ness.team5.capstoneProject.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Service implementaion for LoanService
 * @author Vedant
 */
@Service
@Slf4j
public class LoanServiceImpl implements LoanService{
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanMapper loanMapper;
    @Override
    public void PostLoan(List<Loan> loan) throws IllegalStateException, URISyntaxException {
        if(loan.isEmpty()){
            throw new IllegalStateException("input is empty");
        }
       Validator(loan);
        loanRepository.saveAll(loan);
    }

    @Override
    public Optional<Loan> getLoan(String id) throws IllegalStateException {

        return loanRepository.findById(id);
    }

    @Override
    public String getStatus(String id) throws IllegalStateException {

        Optional<Loan> optionalLoan = loanRepository.findById(id);
        Loan loan = optionalLoan.orElseThrow(()->new IllegalStateException());
        String status = loan.getLoanStatus();
        return status;
    }

    @Override
    public Loan updateLoan(String id, LoanDTO loanDTO) throws IllegalStateException {
        if(loanRepository.findById(id).isEmpty()){
            throw new IllegalStateException("Please Enter valid id");
        }
        Loan loan = loanMapper.convertLoanDTOtoLoanEntity(loanDTO);
        loan.setLoanId(id);
        loanRepository.save(loan);
        return loan;
    }

    @Override
    public HttpStatus deleteLoan(String id) throws IllegalStateException {
        if(!loanRepository.existsById(id)){
            throw new IllegalStateException("Please check the id, id does not exist");
        }
        loanRepository.deleteById(id);

        return HttpStatus.ACCEPTED;
    }


    /**
     * Validator for data to be saved
     * @Author Vedant
     * @param loanlist
     * @return List<Loan>
     * @throws URISyntaxException
     */
    private List<Loan> Validator(List<Loan> loanlist) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String scheme = "http";
        String host = "localhost";
        int port = 8083;
        String path = "/customer/";

        for (Loan loan : loanlist) {
            String customerId = loan.getBorrowerId();
            URI url = new URI(scheme, null, host, port, path + customerId, null, null);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            HttpStatus status = response.getStatusCode();

            if (loan.getLoanId().isEmpty() || loan.getBorrowerId().isEmpty()) {
                log.error("Criteria mismatch Loan Id :{} Borrower Id :{} can't be null", loan.getLoanId(), loan.getBorrowerId());
                loanlist.remove(loan);
            }

            if (loan.getInterestRate() < 0 || loan.getLoanAmount() < 0 || loan.getLoanTerm() > 61) {
                log.error("Criteria mismatch Interest Rate :{} Loan Amount :{} Loan Term:{} can't be null", loan.getInterestRate(), loan.getLoanAmount(), loan.getLoanTerm());
                loanlist.remove(loan);
            }

            if (!status.is2xxSuccessful()) {
                log.error("Unauthorized User :{}", loan.getBorrowerId());
                loanlist.remove(loan);
            }
        }

        return loanlist;
    }



}

