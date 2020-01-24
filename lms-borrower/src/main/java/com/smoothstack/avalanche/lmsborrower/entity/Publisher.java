package com.smoothstack.avalanche.lmsborrower.entity;

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
@Table(name = "tbl_publisher")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;
	
	@OneToMany(mappedBy = "publisher",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Book> books;
	/*
	 * CONSTRUCTORS
	 */
	public Publisher() {}
	public Publisher(Long id, String name, String address, String phone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * Equals/ HashCode
	 */

	@Override
	public boolean equals(Object o)
	{
		if( this == o) return true;
		if( o == null || getClass() != o.getClass())
			return false;
		Publisher other = (Publisher) o;
		return Objects.equals(getName(), other.getName())
				&& Objects.equals(getAddress(), other.getAddress())
				&& Objects.equals(getPhone(), other.getPhone());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(name, address, phone);
	}
}
