package com.smoothstack.avalanche.lms.borrowermsvc.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smoothstack.avalanche.lms.borrowermsvc.entity.BookCopies;
import com.smoothstack.avalanche.lms.borrowermsvc.entity.BookLoans;
import com.smoothstack.avalanche.lms.borrowermsvc.entity.Branch;
import com.smoothstack.avalanche.lms.borrowermsvc.service.BorrowerService;

import javassist.NotFoundException;

@RestController
public class BorrowerController {

	@Autowired
	BorrowerService borrowerService;
	
	private static final Logger logger = LogManager.getLogger(BorrowerController.class);
	/*
	 * Functions for BookLoans
	 */
	
	@GetMapping(path = "/lms/borrower/bookloans/{cardNo}")
	public ResponseEntity<List<BookLoans>> readLoansByCardNo(@Valid @PathVariable("cardNo") Long cardNo) throws NotFoundException{
		logger.info("Borrower: " + cardNo + " has logged in to look at Book Loans.");
		try{
			List<BookLoans> searchLoans = borrowerService.readLoansByCardNo(cardNo);
			return new ResponseEntity<List<BookLoans>>(searchLoans, new HttpHeaders(), HttpStatus.OK);
		} catch(NotFoundException e) {
			logger.error("Loans not found with card_no: " + cardNo);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loans not found with card_no: " + cardNo, e);
		}
	}

	@PostMapping(path = "/lms/borrower/bookloan")
	public ResponseEntity<BookLoans> createLoan(@Valid @RequestBody BookLoans loan){
		logger.info("Book Loans is being created by :" + loan.getBookLoansId().getCardNo() + " with : " + loan.getBookLoansId().getBookId() + " from: " + loan.getBookLoansId().getBranchId());
		try {
			borrowerService.createLoan(loan);
			ResponseEntity<BookLoans> response = new ResponseEntity<BookLoans>(HttpStatus.CREATED);
			return response;
		} catch(IllegalArgumentException e) {
			logger.error("Book loan creation failed: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}
	
	@PutMapping(path = "/lms/borrower/bookloans:bookloans")
	public ResponseEntity<BookLoans> updateLoan(@Valid @RequestBody BookLoans loan) throws NotFoundException {
		logger.info("Book Loans is being updated by :" + loan.getBookLoansId().getCardNo() + " with : " + loan.getBookLoansId().getBookId() + " from: " + loan.getBookLoansId().getBranchId());

		try{
			borrowerService.updateBookLoans(loan);
			ResponseEntity<BookLoans> response= new ResponseEntity<BookLoans>(loan, HttpStatus.ACCEPTED);
			return response;
		} catch(IllegalArgumentException e) {
			logger.error("Book loan update failed: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}
//
//	/*
//	 * Function for Branches
//	 */
	@GetMapping(path ="/lms/borrower/branches")
	public List<Branch> readBranches() throws NotFoundException{
		try{
			List<Branch> searchBranches = borrowerService.readBranches();
			return searchBranches;
		} catch(NotFoundException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	/*
	 * Function for Book Copies
	 */
	@GetMapping(path = "/lms/borrower/bookcopies/{branchId}")
	public List<BookCopies> readBookCopiesByBranch(@Valid @PathVariable("branchId") Long branchId) throws ClassNotFoundException, IllegalArgumentException, SQLException, NotFoundException{
		logger.info("Check Book Copies from: " + branchId);
		try{
			List<BookCopies> searchBookCopies = borrowerService.readBookCopiesByBranch(branchId);
			return searchBookCopies;
		} catch(NotFoundException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@PutMapping(path = "/lms/borrower/bookcopies:bookcopies")
	public ResponseEntity<BookCopies> updateBookCopy(@Valid @RequestBody BookCopies bc) throws ClassNotFoundException, SQLException, NotFoundException{
		logger.info("Checking out book: " + bc.getBookCopiesId().getBookId() + "from :" + bc.getBookCopiesId().getBranchId());
		try{
			borrowerService.updateBookCopies(bc);
			ResponseEntity<BookCopies> response = new ResponseEntity<BookCopies>(bc, HttpStatus.NO_CONTENT);
			return response;
		} catch(NotFoundException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	/*
	 * Function for
	 */
}
