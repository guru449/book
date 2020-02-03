package com.rakuten.experiment.service;

import java.util.List;

import com.rakuten.experiment.domain.Book;


public interface BookService{
	public int addBook(Book b,int pid);
	public void removeBook(int id);
	List<Book> findAll();
	Book findById(int id);
	List<Book> findbyPublisherId(int id);

}
