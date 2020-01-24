package com.smoothstack.avalanche.lmsborrower.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smoothstack.avalanche.lmsborrower.entity.BookLoans;
import com.smoothstack.avalanche.lmsborrower.entity.BookLoansId;


@Repository
public interface BookLoansDAO extends JpaRepository<BookLoans ,BookLoansId>{

	@Query("SELECT bookloans FROM BookLoans bookloans WHERE bookloans.borrower.cardNo = :cardNum")
	List<BookLoans> findByCardNo(@Param("cardNum") int cardNo);
	
	@Query("SELECT bookloan FROM BookLoans bookloan "
			+ "WHERE bookloan.borrower.cardNo = :cardNum AND "
			+ "bookloan.book.bookId = :bookId AND "
			+ "bookloan.branch.branchId = :branchId")
	Optional<BookLoans> findByBookLoanId(@Param("cardNum") Long cardNo, @Param("bookId") Long bookId, @Param("branchId") Long branchId);
}
