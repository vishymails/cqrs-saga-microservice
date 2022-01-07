package com.bvr.events;

public class LibraryCreatedEvent {
	
	private final Integer libraryId;
	
	private final String name;

	public LibraryCreatedEvent(Integer libraryId, String name) {
		super();
		this.libraryId = libraryId;
		this.name = name;
	}

	public Integer getLibraryId() {
		return libraryId;
	}

	public String getName() {
		return name;
	}
	
	
	

}
