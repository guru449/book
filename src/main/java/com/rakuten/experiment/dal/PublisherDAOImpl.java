package com.rakuten.experiment.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rakuten.experiment.domain.Book;
import com.rakuten.experiment.domain.Publisher;

@Repository
@Transactional
public class PublisherDAOImpl  implements PublisherDAO{
	@Autowired
	EntityManager em;
	

	@Override
	public Publisher findById(int id) {
		// TODO Auto-generated method stub
		
		return em.find(Publisher.class, id);
	}

	@Override
	public Publisher save(Publisher toBeSaved) {
		// TODO Auto-generated method stub
		//Book p=em.find(Book.class, bookId);
		em.persist(toBeSaved);
		return toBeSaved;
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Publisher p=em.find(Publisher.class, id);
		em.remove(p);
		
	}

	@Override
	public List<Publisher> findAll() {
		// TODO Auto-generated method stub
		Query q= em.createQuery("select p from Publisher p");
		List<Publisher>list= q.getResultList();
		return list;
	}

	

}
