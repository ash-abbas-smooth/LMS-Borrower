package com.smoothstack.avalanche.lmsborrower.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.avalanche.lmsborrower.entity.BookCopies;
import com.smoothstack.avalanche.lmsborrower.entity.BookLoans;
import com.smoothstack.avalanche.lmsborrower.entity.Branch;
import com.smoothstack.avalanche.lmsborrower.service.BorrowerService;

import javassist.NotFoundException;

@RestController
public class BorrowerController {

	@Autowired
	BorrowerService borrowerService;
	/*
	 * Functions for BookLoans
	 */
	
	@GetMapping(path = "/lms/borrower/bookloans/{cardNo}")
	public List<BookLoans> readLoansByCardNo(@Valid @PathVariable("cardNo") Long cardNo) throws ClassNotFoundException, SQLException, NotFoundException{
		List<BookLoans> searchLoans = borrowerService.readLoansByCardNo(cardNo);
		return searchLoans;
	}

	@PostMapping(path = "/lms/borrower/bookloan")
	public ResponseEntity<BookLoans> createLoan(@Valid @RequestBody BookLoans loan) throws ClassNotFoundException, SQLException{
		borrowerService.createLoan(loan);
		ResponseEntity<BookLoans> response = new ResponseEntity<BookLoans>(HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping(path = "/lms/borrower/bookloans:bookloans")
	public ResponseEntity<String> updateLoan(@Valid @RequestBody BookLoans loan) throws ClassNotFoundException, SQLException, NotFoundException {
		borrowerService.updateBookLoans(loan);
		ResponseEntity<String> response= new ResponseEntity<String>("Update Book Loans Complete!", HttpStatus.ACCEPTED);
		return response;
	}
//
//	/*
//	 * Function for Branches
//	 */
	@GetMapping(path ="/lms/borrower/branches")
	public List<Branch> readBranches() throws ClassNotFoundException, SQLException, NotFoundException{
		List<Branch> searchBranches = borrowerService.readBranches();
		return searchBranches;
	}

	/*
	 * Function for Book Copies
	 */
	@GetMapping(path = "/lms/borrower/bookcopies/{branchId}")
	public List<BookCopies> readBookCopiesByBranch(@Valid @PathVariable("branchId") Long branchId) throws ClassNotFoundException, IllegalArgumentException, SQLException, NotFoundException{
		List<BookCopies> searchBookCopies = borrowerService.readBookCopiesByBranch(branchId);
		return searchBookCopies;
	}

	@PutMapping(path = "/lms/borrower/bookcopies:bookcopies")
	public ResponseEntity<String> updateBookCopy(@Valid @RequestBody BookCopies bc) throws ClassNotFoundException, SQLException, NotFoundException{
		borrowerService.updateBookCopies(bc);
		ResponseEntity<String> response = new ResponseEntity<String>("Update BookCopies complete!", HttpStatus.NO_CONTENT);
		return response;
	}
	/*
	 * Function for
	 */
}
