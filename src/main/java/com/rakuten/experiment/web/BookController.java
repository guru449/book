package com.rakuten.experiment.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.experiment.domain.Book;
import com.rakuten.experiment.domain.Publisher;
import com.rakuten.experiment.service.BookService;
import com.rakuten.experiment.service.NoSuchPublisherException;
import com.rakuten.experiment.service.PublisherService;

@RestController
@CrossOrigin
public class BookController {
	@Autowired
	BookService service;
	@Autowired
	PublisherService pubservice;

	// @RequestMapping(method=RequestMethod.GET,value="/products")
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = service.findAll();
		if (books != null)
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		else {
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBooks(@PathVariable("id") int id) {
		Book book = service.findById(id);
		if (book != null)
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		else {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/publisher/{id}/books")
	public ResponseEntity<List<Book>> getBookForPublisher(@PathVariable("id") int pid) {
		Publisher p = pubservice.findById(pid);
		if (p == null) {
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
		} else {
			List<Book> books = service.findbyPublisherId(pid);
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		}
	}

	@PostMapping("/publisher/{id}/books")
	public ResponseEntity<Book> addBooksToPublisher(@PathVariable("id") int pid, @RequestBody Book book) {
		try {
			int id = service.addBook(book, pid);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/publisher/" + pid + "/books/" + id));
			return new ResponseEntity<Book>(book, headers, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		} catch (IllegalStateException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}

}
