package com.rakuten.experiment.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.experiment.domain.Book;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO {
	@Autowired
	EntityManager em;

	@Override
	public Book save(Book toBeSaved) {
		// TODO Auto-generated method stub
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public Book findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Book.class,id);
		
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		Query q= em.createQuery("select b from Book b");
		List<Book>list= q.getResultList();
		return list;
		
	}

	@Override
	public void delesteById(int id) {
		// TODO Auto-generated method stub
		Book b=em.find(Book.class, id);
		em.remove(b);
		
	}
	@Override
	public List<Book> findBooksByPubisherId(int id) {
		// TODO Auto-generated method stub
		TypedQuery<Book> q=
				em.createQuery("select b from Book b where b.publisher.pubid=:pid", Book.class);
		q.setParameter("pid", id);
		return q.getResultList();
	}
	
	
	
	

}
