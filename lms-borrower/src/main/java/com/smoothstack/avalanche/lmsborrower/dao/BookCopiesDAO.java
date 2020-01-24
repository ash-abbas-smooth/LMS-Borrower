package com.smoothstack.avalanche.lmsborrower.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smoothstack.avalanche.lmsborrower.entity.BookCopies;
import com.smoothstack.avalanche.lmsborrower.entity.BookCopiesId;


@Repository
public interface BookCopiesDAO extends JpaRepository<BookCopies , BookCopiesId>{

	@Query("SELECT bc FROM BookCopies bc WHERE bc.branch.id = :brid")
	List<BookCopies> findBookCopiesByBranch(@Param("brid") Long branchId);
	
	@Query("SELECT bc FROM BookCopies bc WHERE bc.book.id = :bid AND bc.branch.id = :brid")
	Optional<BookCopies> findBookCopiesById(@Param("bid") Long bookId, @Param("brid") Long branchId);
}
