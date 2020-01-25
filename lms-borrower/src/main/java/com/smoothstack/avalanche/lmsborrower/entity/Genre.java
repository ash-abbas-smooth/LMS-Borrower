package com.smoothstack.avalanche.lmsborrower.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tbl_genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "tbl_book_genres",
			joinColumns = {@JoinColumn(name = "genre_id")},
			inverseJoinColumns = { @JoinColumn(name = "book_id")}
			)
	private List<Book> books;
	
	/*
	 * CONSTRUCTORS
	 */
	public Genre() {}
	public Genre(Long id, String name) {
		this.id = id;
		this.name = name;
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
	
	/*
	 * Equals/ HashCode
	 */
	@Override
	public boolean equals(Object o)
	{
		if( this == o) return true;
		if( o == null || getClass() != o.getClass())
			return false;
		Genre other = (Genre) o;
		return Objects.equals(getName(), other.getName());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(name);
	}
}
