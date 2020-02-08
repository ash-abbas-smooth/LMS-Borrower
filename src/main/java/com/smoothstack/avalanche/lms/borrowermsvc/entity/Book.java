package com.smoothstack.avalanche.lms.borrowermsvc.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@ManyToMany(mappedBy = "books", 
			cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Author> authors;
	
	@ManyToMany(mappedBy = "books", 
			cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Genre> genres;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Publisher publisher;
	
	
	public Book() {}

	public Book(Long id, String title,Publisher publisher) {
		this.id = id;
		this.title = title;
		this.publisher = publisher;
	}

	/*
	 * GETTERS / SETTERS
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if( o == null || getClass() != o.getClass()) return false;
		Book other = (Book) o;
		return Objects.equals(getTitle(), other.getTitle()) && Objects.equals(getAuthors(), other.getAuthors())
				&& Objects.equals(getPublisher(), other.getPublisher());
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(title);
	}
}
