package com.rakuten.experiment.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.experiment.domain.Book;
import com.rakuten.experiment.service.BookService;
import com.rakuten.experiment.service.BookServiceImpl;
@Component("uiObj")
public class BookConsoleUI {
	BookService b;
@Autowired
	public void setB(BookService b) {
		this.b = b;
	}
	public void createBookWithUI() {
	    Scanner s = new Scanner(System.in);
	    System.out.println("Enter Name: ");
	    String name = s.nextLine();
	    System.out.println("Enter Genre: ");
	    String genre = s.nextLine();
	    System.out.println("Enter Pages: ");
	    int pages = Integer.parseInt(s.nextLine());
	    System.out.println("etner price:");
	    float price=Float.parseFloat(s.nextLine());

	    Book p = new Book(name, genre, pages,price);
	    //int id = b.addBook(p);
	    //sSystem.out.println("Created Book  with Id: " + id);
	  }

	
	

}
