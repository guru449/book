package com.rakuten.experiment.service;

import java.awt.print.Book;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.experiment.dal.PublisherDAO;
import com.rakuten.experiment.domain.Publisher;
@Transactional
@Service
public class PublisherServiceImpl implements PublisherService {

	PublisherDAO dao;
	
@Autowired
	public void setDao(PublisherDAO dao) {
		this.dao = dao;
	}

	@Override
	public int addPublisher(Publisher toBeAdded) {
		// TODO Auto-generated method stub
		Publisher p=dao.save(toBeAdded);
		return p.getpubid();
	}

	@Override
	public void removePublisher(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<Publisher> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Publisher findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}


	
}
