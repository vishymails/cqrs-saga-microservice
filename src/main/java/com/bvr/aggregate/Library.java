package com.bvr.aggregate;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import com.bvr.commands.RegisterBookCommand;
import com.bvr.commands.RegisterLibraryCommand;
import com.bvr.events.BookCreatedEvent;
import com.bvr.events.LibraryCreatedEvent;


@Aggregate
public class Library {

	
	@AggregateIdentifier
	private Integer libraryId;
	
	private String name;
	
	private List<String> isbnBooks;
	
	protected Library() {
		//axon initialization will happen 
	}
	
	
	@CommandHandler
	public Library(RegisterLibraryCommand cmd) {
		Assert.notNull(cmd.getLibraryId(), "ID should not be null");
		Assert.notNull(cmd.getName(), "Name should not be null");
		
		AggregateLifecycle.apply(new LibraryCreatedEvent(cmd.getLibraryId(), cmd.getName()));
	}


	public Integer getLibraryId() {
		return libraryId;
	}


	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<String> getIsbnBooks() {
		return isbnBooks;
	}


	public void setIsbnBooks(List<String> isbnBooks) {
		this.isbnBooks = isbnBooks;
	}
	
	
	@CommandHandler
	public void addBook(RegisterBookCommand cmd) {
		Assert.notNull(cmd.getLibraryId(), "ID should not be null");
		Assert.notNull(cmd.getIsbn(), "Book Isbn Name should not be null");
		
		AggregateLifecycle.apply(new BookCreatedEvent(cmd.getLibraryId(), cmd.getIsbn(), cmd.getTitle()));
	}
	
	
	@EventSourcingHandler
	private void handleCreatedEvent(LibraryCreatedEvent event) {
		libraryId = event.getLibraryId();
		name = event.getName();
		isbnBooks =new ArrayList<>();
	}
	
	
	
	@EventSourcingHandler
	private void addBook(BookCreatedEvent event) {
		isbnBooks.add(event.getIsbn());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
