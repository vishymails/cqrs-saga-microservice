package com.bvr.queries;

public class GetBooksQuery {

	private final Integer libaryId;

	public GetBooksQuery(Integer libaryId) {
		super();
		this.libaryId = libaryId;
	}

	public Integer getLibaryId() {
		return libaryId;
	}
	
	
}
