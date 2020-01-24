package com.smoothstack.avalanche.lmsborrower.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BookCopiesId implements Serializable{
	
	private Long bookId;
	private Long branchId;
	
	public BookCopiesId() {
		
	}
	public BookCopiesId(Long bookId, Long branchId) {
		super();
		this.bookId = bookId;
		this.branchId = branchId;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
}
