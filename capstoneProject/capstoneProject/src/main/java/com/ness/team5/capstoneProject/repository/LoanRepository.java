package com.ness.team5.capstoneProject.repository;

import com.ness.team5.capstoneProject.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for LoanService
 * @author Vedant
 */

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
}
