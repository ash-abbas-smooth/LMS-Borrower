package com.smoothstack.avalanche.lmsborrower.entity;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_book_copies")
public class BookLoans {

	@EmbeddedId
	private BookLoansId bookLoansId;
	
	@MapsId("bookId")
	@ManyToOne
	private Book book;
	
	@MapsId("branchId")
	@ManyToOne
	private Branch branch;
	
	@MapsId("cardNo")
	@ManyToOne
	private Borrower borrower;
	
	@Column(name = "date_out")
	private Date dateOut;
	
	@Column(name = "date_due")
	private Date dateDue;
	
	@Column(name = "date_in")
	private Date dateIn;

	/*
	 * CONSTRUCTOR
	 */

	public BookLoans() {
		
	}

	public BookLoans(BookLoansId bookLoansId, Date dateOut, Date dateDue, Date dateIn) {

		this.bookLoansId = bookLoansId;
		this.dateOut = dateOut;
		this.dateDue = dateDue;
		this.dateIn = dateIn;
	}

	/*
	 * GETTERS/SETTERS
	 */
	public BookLoansId getBookLoansId() {
		return bookLoansId;
	}

	public void setBookLoansId(BookLoansId bookLoansId) {
		this.bookLoansId = bookLoansId;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDateDue() {
		return dateDue;
	}

	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	
	/*
	 * EQUALS / HASHCODE
	 */
	@Override
	public boolean equals(Object o)
	{
		if( this == o) return true;
		if( o == null || getClass() != o.getClass())
			return false;
		BookLoans other = (BookLoans) o;
		return Objects.equals(getBook(), other.getBook()) && 
				Objects.equals(getBranch(), other.getBranch()) &&
				Objects.equals(getBorrower(), other.getBorrower()) &&
				Objects.equals(getDateOut(), other.getDateOut());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(bookLoansId);
	}
}
