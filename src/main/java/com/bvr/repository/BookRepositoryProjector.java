package com.bvr.repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.bvr.events.BookCreatedEvent;
import com.bvr.queries.GetBooksQuery;

import com.bvr.models.*;

@Service
public class BookRepositoryProjector {

	private final BookRepository bookRepository;

	public BookRepositoryProjector(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
	
	
	@EventHandler
	public void addBook(BookCreatedEvent event) throws Exception {
		BookEntity book = new BookEntity();
		
		book.setIsbn(event.getIsbn());
		book.setLibraryId(event.getLibraryId());
		book.setTitle(event.getTitle());
		bookRepository.save(book);
	}
	
	
	
	@QueryHandler
	public List<BookBean> getBooks(GetBooksQuery query) {
		return bookRepository.findByLibraryId(query.getLibaryId())
				.stream().map(toBook())
				.collect(Collectors.toList());
	}


	
	
	private Function<BookEntity, BookBean> toBook() {
		// TODO Auto-generated method stub
		return e -> {
			BookBean book = new BookBean();
			book.setIsbn(e.getIsbn());
			book.setTitle(e.getTitle());
			return book;
		};
	}
}

		
