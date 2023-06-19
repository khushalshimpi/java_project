package com.ness.team5.capstoneProject.controller;

import com.ness.team5.capstoneProject.dto.LoanDTO;
import com.ness.team5.capstoneProject.entity.Loan;
import com.ness.team5.capstoneProject.service.LoanService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Rest Controller for LoanService
 * @author Vedant
 */


@RestController
@RequestMapping("/loanService/v1/loan")

//@Api(tags = "Loans")
public class LoanController {
    @Autowired
    LoanService loanService;



    /**
     * post mapping for loan service
     * @param loan
     * @return Http status
     */
    @PostMapping("/post")
    //@ApiOperation("Create a new Loan")
    public HttpStatus postLoan(@RequestBody List<Loan> loan) throws URISyntaxException {
        loanService.PostLoan(loan);
        return HttpStatus.ACCEPTED;
    }

    /**
     * Get mapping for loan service
     * @param id
     * @return Loan
     */
    @GetMapping("/{loanid}")
    //@ApiOperation("Get Loan by Id")
    @ResponseBody
    public Optional<Loan> getLoan(@PathVariable("loanid")  String id)
    {

        return loanService.getLoan(id);
    }

    /**
     * Get mapping for loan service
     * @param id
     * @return String
     */
    @GetMapping("/{loanid}/status")
   // @ApiOperation("Get Loan status using Id")
    @ResponseBody
    public String getStatus( @PathVariable("loanid") String id){
//        Optional<Loan> status = getData(id).find

        return loanService.getStatus(id);
    }

    /**
     * Put mapping for loan service
     * @param loanDTO
     * @param id
     * @return LoanDto
     */
    @PatchMapping("/{loanid}/update")
   // @ApiOperation("Patch fields in loan using Id")
    public Loan updateLoan(@RequestBody LoanDTO loanDTO, @PathVariable("loanid") String id){
        try {
            return loanService.updateLoan(id,loanDTO);
        }catch (Exception E){
            E.printStackTrace();
        }

        return null;
    }

    /**
     * Delete mapping for loan service
     * @param id
     * @return Https Status
     */
    @DeleteMapping("/{loanid}/delete")
   // @ApiOperation("Delete a Loan record")
    public HttpStatus deleteLoan(@PathVariable("loanid") String id){
        try {
            return loanService.deleteLoan(id);
        }catch (Exception E){
            E.printStackTrace();
        }
        return null;
    }

    @GetMapping("/")
   // @ApiOperation("Home page")
    public String home() {
        return "Welcome to the Loan API!";
    }


}
