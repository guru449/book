package com.rakuten.experiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rakuten.experiment.dal.PublisherDAO;
import com.rakuten.experiment.domain.Publisher;
import com.rakuten.experiment.ui.BookConsoleUI;

@SpringBootApplication

public class ExperimentApplication {

	public static void main(String[] args) {
//		ApplicationContext springContainer=
				SpringApplication.run(ExperimentApplication.class, args);
	//BookConsoleUI ui=springContainer.getBean(BookConsoleUI.class);
	//ui.createBookWithUI();
//				PublisherDAO publisherDAO=springContainer.getBean(PublisherDAO.class);
//				Publisher sample=new Publisher("Guru","Bangaloare");
//				Publisher saved=publisherDAO.save(sample, 1);
	}

}
