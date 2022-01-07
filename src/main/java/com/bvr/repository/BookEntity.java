package com.bvr.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookEntity {
	
	@Id
	private String isbn;
	
	@Column
	private int libraryId;
	
	@Column 
	private String title;

	public BookEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookEntity(String isbn, int libraryId, String title) {
		super();
		this.isbn = isbn;
		this.libraryId = libraryId;
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
