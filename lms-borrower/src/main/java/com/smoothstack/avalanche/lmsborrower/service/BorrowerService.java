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
	public List<BookLoans> readLoansByCardNo(Long cardNo) throws ClassNotFoundException, SQLException {
		List<BookLoans> searchLoans = loansDAO.findByCardNo(cardNo);
		return searchLoans == null? Collections.EMPTY_LIST : searchLoans;
	}
	

	public void updateBookLoans(BookLoans loan) throws ClassNotFoundException, SQLException, IllegalArgumentException {
		Optional<BookLoans> searchLoan = loansDAO.findByBookLoanId(loan.getBookLoansId().getCardNo(), loan.getBookLoansId().getBookId(), loan.getBookLoansId().getBranchId());
		searchLoan.orElseThrow(() -> new SQLException("Loan not found:" + loan.toString()));
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
	public List<Branch> readBranches() throws ClassNotFoundException, SQLException {
		List<Branch> searchBranches = branchDAO.findAll();
		return searchBranches == null? Collections.EMPTY_LIST : searchBranches;
	}

	public List<BookCopies> readBookCopiesByBranch(Long branchID) throws ClassNotFoundException, SQLException, IllegalArgumentException {
		if(branchID == 0)
			throw new IllegalArgumentException("Cannot be 0");
		List<BookCopies> searchCopies = copiesDAO.findBookCopiesByBranch(branchID);
		return searchCopies == null? Collections.EMPTY_LIST : searchCopies;
	}
	
    public void updateBookCopies(BookCopies copies) throws ClassNotFoundException, SQLException, IllegalArgumentException {
    	Optional<BookCopies> searchBookCopies = copiesDAO.findBookCopiesById(copies.getBook().getId(), copies.getBranch().getId());
    	searchBookCopies.orElseThrow(() -> new IllegalArgumentException("Book Copies Not Found"));
    	copiesDAO.save(copies);
    }

	public void createLoan(BookLoans loan) throws ClassNotFoundException, SQLException, IllegalArgumentException {
		Optional<BookLoans> searchLoan = loansDAO.findByBookLoanId(loan.getBookLoansId().getCardNo(), loan.getBookLoansId().getBookId(), loan.getBookLoansId().getBranchId());
		if(searchLoan.isPresent())
		{
			throw new IllegalArgumentException("Loans already exist with Id: BookId:" + loan.getBookLoansId().getBookId() +" BranchId:" + loan.getBookLoansId().getBranchId() 
					+ " CardNo:"+ loan.getBookLoansId().getCardNo());
		}
		loansDAO.save(loan);
	}
}