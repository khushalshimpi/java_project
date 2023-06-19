package com.ness.team5.capstoneProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

/**
 * DTO class for Loan
 * @author Vedant
 */

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanDTO {

    @JsonProperty("borrowerId")
    private String borrowerId;

    @JsonProperty("loanAmount")
    private Double loanAmount;
    @JsonProperty("interestRate")
    private Double interestRate;

    @JsonProperty("loanTerm")
    private Integer loanTerm;

    @JsonProperty("paymentFrequency")
    private String paymentFrequency;

    @JsonProperty("loanStatus")
    private String loanStatus;
    @JsonProperty("paymentAmount")
    private Double paymentAmount;




}
