package com.smoothstack.avalanche.lmsborrower.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class BookCopiesId implements Serializable{
	
	private Long bookId;
	
	private Long branchId;
	/*
	 * CONSTRUCTOR
	 */
	public BookCopiesId() {
		
	}
	public BookCopiesId(Long bookId, Long branchId) {
		super();
		this.bookId = bookId;
		this.branchId = branchId;
	}
	/*
	 * GETTERS / SETTERS
	 */
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
	/*
	 * EQUALS/HASHCODE
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopiesId other = (BookCopiesId) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		return true;
	}
}
