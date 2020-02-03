package com.rakuten.experiment.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.experiment.domain.Book;
import com.rakuten.experiment.domain.Publisher;
import com.rakuten.experiment.service.BookService;
import com.rakuten.experiment.service.PublisherService;

@RestController
@CrossOrigin
public class PublisherController {
	@Autowired
	PublisherService service;

	// @RequestMapping(method=RequestMethod.GET,value="/products")
	@GetMapping("/publisher")
	public List<Publisher> getAllPublisher() {
		return service.findAll();
	}

	@GetMapping("/publisher/{id}") // URI path template
	public ResponseEntity<Publisher> getPublisherById(@PathVariable("id") int id) {
		Publisher p = service.findById(id);
		if (p != null) {
			return new ResponseEntity<Publisher>(p, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/publisher")
	public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher toBeAdded) {
		try {
			int id = service.addPublisher(toBeAdded);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/publisher/" + id));
			return new ResponseEntity<Publisher>(toBeAdded, headers, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Publisher>(HttpStatus.BAD_REQUEST);
		}
	}

}
