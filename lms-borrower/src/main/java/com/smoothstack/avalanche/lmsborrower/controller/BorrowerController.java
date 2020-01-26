package com.smoothstack.avalanche.lmsborrower.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
	public ResponseEntity<List<BookLoans>> readLoansByCardNo(@Valid @PathVariable("cardNo") Long cardNo) throws NotFoundException{
		try{
			List<BookLoans> searchLoans = borrowerService.readLoansByCardNo(cardNo);
			return new ResponseEntity<List<BookLoans>>(searchLoans, new HttpHeaders(), HttpStatus.OK);
		} catch(NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loans not found with card_no: " + cardNo, e);
		}
	}

	@PostMapping(path = "/lms/borrower/bookloan")
	public ResponseEntity<BookLoans> createLoan(@Valid @RequestBody BookLoans loan){
		try {
			borrowerService.createLoan(loan);
			ResponseEntity<BookLoans> response = new ResponseEntity<BookLoans>(HttpStatus.CREATED);
			return response;
		} catch(IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}
	
	@PutMapping(path = "/lms/borrower/bookloans:bookloans")
	public ResponseEntity<String> updateLoan(@Valid @RequestBody BookLoans loan) throws ClassNotFoundException, SQLException, NotFoundException {
		try{
			borrowerService.updateBookLoans(loan);
			ResponseEntity<String> response= new ResponseEntity<String>("Update Book Loans Complete!", HttpStatus.ACCEPTED);
			return response;
		} catch(IllegalArgumentException e) {
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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	/*
	 * Function for Book Copies
	 */
	@GetMapping(path = "/lms/borrower/bookcopies/{branchId}")
	public List<BookCopies> readBookCopiesByBranch(@Valid @PathVariable("branchId") Long branchId) throws ClassNotFoundException, IllegalArgumentException, SQLException, NotFoundException{
		try{
			List<BookCopies> searchBookCopies = borrowerService.readBookCopiesByBranch(branchId);
			return searchBookCopies;
		} catch(NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@PutMapping(path = "/lms/borrower/bookcopies:bookcopies")
	public ResponseEntity<String> updateBookCopy(@Valid @RequestBody BookCopies bc) throws ClassNotFoundException, SQLException, NotFoundException{
		try{
			borrowerService.updateBookCopies(bc);
			ResponseEntity<String> response = new ResponseEntity<String>("Update BookCopies complete!", HttpStatus.NO_CONTENT);
			return response;
		} catch(NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	/*
	 * Function for
	 */
}
