package com.rakuten.experiment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.experiment.dal.BookDAO;
import com.rakuten.experiment.dal.PublisherDAO;
import com.rakuten.experiment.domain.Book;
import com.rakuten.experiment.domain.Publisher;
@Transactional
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookDAO dao;
	@Autowired
	PublisherDAO pubDAO;
	@Autowired
	public void setPubDAO(PublisherDAO pubDAO) {
		this.pubDAO = pubDAO;
	}
@Autowired
	public void setB(BookDAO b) {
		this.dao = b;
	}


	@Override
	public int addBook(Book b,int pid) { //method stub

	Publisher publisher=pubDAO.findById(pid);
	if(publisher==null)
		throw new IllegalStateException("publisher is null");
	else {
		if(b.getPages()>1000 || b.getGenre().equalsIgnoreCase("textbook") )
		{
			System.out.println("cannot add");
			throw new IllegalArgumentException("book has more page");
		}
		else {
		Book saved=dao.save(b);
		saved.setPublisher(publisher);
		return saved.getId();
		}
	
	}
	}
	
	@Override
	public void removeBook(int id) {
		// TODO Auto-generated method stub
		Book b=dao.findById(id);
		if(b.getGenre().equalsIgnoreCase("philosopher"))
			{
			throw new IllegalArgumentException("philospoher book cant be deleted");
			}
		else
			dao.delesteById(id);
		
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Book findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Book> findbyPublisherId(int id) {
		// TODO Auto-generated method stub
		return dao.findBooksByPubisherId(id);
	}
	
}
