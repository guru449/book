package com.rakuten.experiment.dal;


import java.util.List;

import com.rakuten.experiment.domain.Book;
import com.rakuten.experiment.domain.Publisher;

public interface PublisherDAO {
	Publisher findById(int id);

	   Publisher save(Publisher toBeSaved) ;

	  void deleteById(int id);

	  List<Publisher> findAll();
	  
	  

}
