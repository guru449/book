package com.rakuten.experiment.dal;

import java.util.List;

import com.rakuten.experiment.domain.Book;

public interface BookDAO {
Book save(Book toBeSaved);
	
	Book findById(int id);
	List<Book> findAll();
	
	void delesteById(int id);
	public List<Book> findBooksByPubisherId(int id);
}
