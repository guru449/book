package com.rakuten.experiment.service;

import java.awt.print.Book;
import java.util.List;

import com.rakuten.experiment.domain.Publisher;

public interface PublisherService {
	int addPublisher(Publisher toBeAdded);
	void removePublisher(int id);
	List<Publisher> findAll();
	Publisher findById(int id);
	
}
