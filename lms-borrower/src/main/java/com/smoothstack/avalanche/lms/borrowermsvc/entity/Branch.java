package com.smoothstack.avalanche.lms.borrowermsvc.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_library_branch")
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "branch", 
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<BookLoans> bookLoans;
	
	@OneToMany(mappedBy = "branch", 
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<BookCopies> bookCopies;
	
	/*
	 * CONSTRUCTORS
	 */
	public Branch() {}
	public Branch(Long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	/*
	 * GETTERS/SETTERS
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/*
	 * EQUALS / HASHCODE
	 */
	/*
	 * Equals/ HashCode
	 */
	@Override
	public boolean equals(Object o)
	{
		if( this == o) return true;
		if( o == null || getClass() != o.getClass())
			return false;
		Branch other = (Branch) o;
		return Objects.equals(getName(), other.getName()) && Objects.equals(getAddress(), other.getAddress());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(name, address);
	}
}
