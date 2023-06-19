package com.ness.team5.capstoneProject.mapper;

import com.ness.team5.capstoneProject.dto.LoanDTO;
import com.ness.team5.capstoneProject.entity.Loan;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
/**
 * Mapper class for LoanService
 * @author Vedant
 */

@Component
public class LoanMapper {
    /**
     * Function to convert DTO to Entity
     * @param loanDTO
     * @return Loan
     */
    public Loan convertLoanDTOtoLoanEntity(LoanDTO loanDTO){
        Loan loan = new Loan();
        BeanUtils.copyProperties(loanDTO,loan);
        return loan;
    }
}
