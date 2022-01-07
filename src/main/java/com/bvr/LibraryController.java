package com.bvr;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.tomcat.jni.Library;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bvr.commands.RegisterBookCommand;
import com.bvr.commands.RegisterLibraryCommand;
import com.bvr.models.BookBean;
import com.bvr.models.LibaryBean;
import com.bvr.queries.GetBooksQuery;
import com.bvr.queries.GetLibraryQuery;

@RestController
public class LibraryController {

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;
	
	@Autowired
	public LibraryController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
		
		
	}
	
	
	@PostMapping("/api/library")
	public String addLibrary(@RequestBody LibaryBean library) {
		commandGateway.send(new RegisterLibraryCommand(library.getLibraryId(), library.getName()));
		return "Saved";
	}
	
	@GetMapping("/api/library/{library}")
	public Library getLibrary(@PathVariable Integer library) throws InterruptedException, ExecutionException {
		CompletableFuture<Library> future = queryGateway.query(new GetLibraryQuery(library), Library.class);
		return future.get();
	}
	
	
	
	@PostMapping("/api/library/{library}/book")
	public String addBook(@PathVariable Integer library, @RequestBody BookBean book) {
		commandGateway.send(new RegisterBookCommand(library, book.getIsbn(), book.getTitle() ));
		return "Saved";
	}
	
	@GetMapping("/api/library/{library}/book")
	public List<BookBean> getBook(@PathVariable Integer library) throws InterruptedException, ExecutionException {
		return queryGateway.query(new GetBooksQuery(library), ResponseTypes.multipleInstancesOf(BookBean.class)).get();
	}
}
