package com.smoothstack.avalanche.lmsborrower.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.avalanche.lmsborrower.dao.AuthorDAO;
import com.smoothstack.avalanche.lmsborrower.dao.BookCopiesDAO;
import com.smoothstack.avalanche.lmsborrower.dao.BookLoansDAO;
import com.smoothstack.avalanche.lmsborrower.dao.BranchDAO;
import com.smoothstack.avalanche.lmsborrower.entity.BookCopies;
import com.smoothstack.avalanche.lmsborrower.entity.BookLoans;
import com.smoothstack.avalanche.lmsborrower.entity.Branch;

import javassist.NotFoundException;

@Service
@Transactional
public class BorrowerService{
	
	@Autowired
	private BookLoansDAO loansDAO;
	
	@Autowired
	private BranchDAO branchDAO;
	
	@Autowired
	private BookCopiesDAO copiesDAO;
	
	@Autowired
	private AuthorDAO authorDAO;

	/*
	 * Functions for returning a book
	 */
	public List<BookLoans> readLoansByCardNo(Long cardNo) throws ClassNotFoundException, SQLException, NotFoundException {
		if(cardNo == 0) {
			throw new IllegalArgumentException("Cannot be 0");
		}
		List<BookLoans> searchLoans = loansDAO.findByCardNo(cardNo);
		if(searchLoans.size() <= 0) {
			throw new NotFoundException("Loans not found from card_no: " + cardNo);
		}
		return searchLoans;
	}
	

	public void updateBookLoans(BookLoans loan) throws ClassNotFoundException, SQLException, NotFoundException {
		Optional<BookLoans> searchLoan = loansDAO.findById(loan.getBookLoansId());
		searchLoan.orElseThrow(() -> new IllegalArgumentException("Loan not found:" + loan.toString()));
		loansDAO.save(loan);
	}

	/*
	 * Functions for returning a list of books based on author
	 */
	/*
	 * TODO: Create in DAO readBookList(author author) if needed
	 */
	
	/*
	 * Functions for checking out a book
	 */
	public List<Branch> readBranches() throws ClassNotFoundException, SQLException, NotFoundException {
		List<Branch> searchBranches = branchDAO.findAll();
		if(searchBranches.size() <= 0) {
			throw new NotFoundException("Branches not found");
		}
		return searchBranches;
	}

	public List<BookCopies> readBookCopiesByBranch(Long branchId) throws ClassNotFoundException, SQLException, IllegalArgumentException, NotFoundException {
		if(branchId == 0)
			throw new IllegalArgumentException("Cannot be 0");
		System.out.println("branchId is" + branchId);
		List<BookCopies> searchCopies = copiesDAO.findBookCopiesByBranchId(branchId);
		if(searchCopies.size() <= 0) {
			throw new NotFoundException("Book Copies not found with Branch Id: " + branchId);
		}
		return searchCopies;
	}
	
    public void updateBookCopies(BookCopies copies) throws ClassNotFoundException, SQLException, NotFoundException {
    	Optional<BookCopies> searchBookCopies = copiesDAO.findById(copies.getBookCopiesId());
    	searchBookCopies.orElseThrow(() -> new NotFoundException("Book Copies Not Found"));
    	copiesDAO.save(copies);
    }

	public void createLoan(BookLoans loan) throws ClassNotFoundException, SQLException, IllegalArgumentException {
		if(loan == null || loan.getBookLoansId().getBookId() == 0 || loan.getBookLoansId().getBranchId() == 0 || loan.getBookLoansId().getCardNo()== 0) {
			throw new IllegalArgumentException("Inproper Input");
		}
		Optional<BookLoans> searchLoan = loansDAO.findById(loan.getBookLoansId());
		if(searchLoan.isPresent()) {
			throw new IllegalArgumentException("Loans already exist with Id: BookId:" + loan.getBookLoansId().getBookId() +" BranchId:" + loan.getBookLoansId().getBranchId() 
					+ " CardNo:"+ loan.getBookLoansId().getCardNo());
		}
		loansDAO.save(loan);
	}
}