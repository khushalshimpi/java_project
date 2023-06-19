package com.ness.team5.capstoneProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity class for Loan
 * @author Vedant
 */
@Entity
@Data
@Table(name = "LoanTable")
public class Loan {
    /**
     * Represents id for loan table
     */
    @Id
    @Column(name = "loan_id")
    private String loanId;

    /**
     * Represents borrower id column for loan database
     */
    @Column(name ="borrower_id")
    private String borrowerId;

    /**
     * Represents loan amount column for loan database
     */
    @Column(name = "loan_amount")
    private Double loanAmount;

    /**
     * Represents interest rate column for loan database
     */
    @Column(name = "interest_rate")
    private Double interestRate;

    /**
     * Represents loan term column for loan database
     */
    @Column(name = "loan_term")         //length of time over which loan will be repaid
    private Integer loanTerm;

    /**
     * Represents payment frequency column for loan database
     */
    @Column(name="payment_frequency")       //how often payment will be made (eg: monthly, weekly,etc)
    private String paymentFrequency;

    /**
     * Represents payment amount column for loan database
     */
    @Column(name = "payment_amount")
    private Double paymentAmount;

    /**
     * Represents loan status column for loan database
     */
    @Column(name = "loan_status")           //Whether the loan is active, paidOff or in a default
    private String loanStatus;

    /**
     * Represents date of loan column for loan database
     */
    @Column(name = "date_of_loan")         //Date at which loan was issued
    private Date dateOfLoan;

    /**
     * Represents date of first payment column for loan database
     */
    @Column(name="date_of_first_payment")
    private Date dateOfFirstPayment;

    /**
     * Represents loan type column for loan database
     */
    @Column(name = "loanType")              //personal,motor
    private String loanType;

    @Override
    public String toString() {
        return "Loan{" +
                "loanId='" + loanId + '\'' +
                ", borrowerId='" + borrowerId + '\'' +
                ", loanAmount=" + loanAmount +
                ", interestRate=" + interestRate +
                ", loanTerm=" + loanTerm +
                ", paymentFrequency='" + paymentFrequency + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", loanStatus='" + loanStatus + '\'' +
                ", dateOfLoan=" + dateOfLoan +
                ", dateOfFirstPayment=" + dateOfFirstPayment +
                ", loanType='" + loanType + '\'' +
                '}';
    }
}
