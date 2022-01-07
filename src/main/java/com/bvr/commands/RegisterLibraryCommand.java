package com.bvr.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.stereotype.Component;


public class RegisterLibraryCommand {

	@TargetAggregateIdentifier
	private final Integer libraryId;
	
	private final String name;

	public RegisterLibraryCommand(Integer libraryId, String name) {
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
