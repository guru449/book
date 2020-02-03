package com.rakuten.experiment.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.rakuten.experiment.dal.BookDAO;
import com.rakuten.experiment.dal.PublisherDAO;
import com.rakuten.experiment.domain.Book;
import com.rakuten.experiment.domain.Publisher;
import com.rakuten.experiment.service.BookServiceImpl;
import com.rakuten.experiment.service.NoSuchPublisherException;

public class BookServiceImplTest {

	@Test
	public void addNewBook_WHen_Successfully_adds_a_book() {
		// Arrange
		BookServiceImpl sv = new BookServiceImpl();

		Book b = new Book("test", "kuch bhi", 100, 900);
		int id = 1;
		Publisher pub = new Publisher("sourav", "kol");

		pub.setId(id);
		b.setId(id);
		b.setPublisher(pub);

		PublisherDAO mockDAO = Mockito.mock(PublisherDAO.class);
		BookDAO mockbook = Mockito.mock(BookDAO.class);

		Mockito.when(mockDAO.findById(id)).thenReturn(pub);
		sv.setPubDAO(mockDAO);

		Mockito.when(mockbook.save(b)).thenReturn(b);
		sv.setB(mockbook);

		// Act
		int tid = sv.addBook(b, id);
		// Assert
		assertTrue(tid > 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addNewBook_Throws_Exception_If_Publisher_IsNotFound() {
//			BookServiceImpl sv=new BookServiceImpl();
//			 Book b=new Book("test","kuch bhi",100,900);int id=1;
//			  Publisher pub=null;
//			  b.setId(id);
//			  b.setPublisher(pub);
//			  
//			  PublisherDAO mockDAO=Mockito.mock(PublisherDAO.class);
//			  BookDAO mockbook=Mockito.mock(BookDAO.class);
//			  Mockito.when(mockDAO.findById(id)).thenReturn(pub);
//			  sv.setPubDAO(mockDAO);
//			  
//			  //Mockito.when(mockbook.save(b)).thenReturn(b);
//			  sv.setB(mockbook);
		BookServiceImpl sv = new BookServiceImpl();
		Book b = new Book("test", "kuch bhi", 100, 900);
		int id = 1;
//			  Publisher pub=new Publisher("sourav","kol");
//			  
//			  pub.setId(id);
//			  b.setId(id);
//			  b.setPublisher(pub);
		PublisherDAO mockDAO = Mockito.mock(PublisherDAO.class);
		// BookAppDAO mockbook=Mockito.mock(BookAppDAO.class);
		Mockito.when(mockDAO.findById(id + 1)).thenReturn(null);
		sv.setPubDAO(mockDAO);
		// Act
		sv.addBook(b, id);

	}

	@Test(expected = IllegalArgumentException.class)
	public void addNewBook_WHen_cant_add_a_book_IFpages__GT1000() {
		// Arrange
		BookServiceImpl sv = new BookServiceImpl();

		Book b = new Book("test", "kuch bhi", 10000, 900);
		int id = 1;
		Publisher pub = new Publisher("sourav", "kol");

		pub.setId(id);
		b.setId(id);
		b.setPublisher(pub);

		PublisherDAO mockDAO = Mockito.mock(PublisherDAO.class);
		BookDAO mockbook = Mockito.mock(BookDAO.class);

		Mockito.when(mockDAO.findById(id)).thenReturn(pub);
		sv.setPubDAO(mockDAO);

		Mockito.when(mockbook.save(b)).thenReturn(b);
		sv.setB(mockbook);

		// Act
		sv.addBook(b, id);
		// Assert

	}

	@Test
	public void removeBook_removes_book() {
		BookServiceImpl sv = new BookServiceImpl();
		Book b = new Book("test", "kuch bhi", 10000, 900);
		int id = 1;
		b.setId(id);
		BookDAO mockbook = Mockito.mock(BookDAO.class);
		PublisherDAO mockDAO = Mockito.mock(PublisherDAO.class);
		Book bb = mockbook.findById(id);
		Mockito.when(mockbook.findById(id)).thenReturn(b);
		sv.setB(mockbook);
		sv.setPubDAO(mockDAO);
		sv.removeBook(id);
		Mockito.verify(mockbook).delesteById(id);
//			 @Test
//			  public void deleteBook_when_genre_is_not_philosophy()
//			  {
//				  //Arrange
//				  BookServiceImpl sv=new BookServiceImpl();
//				  BookAppDAO mockbook=Mockito.mock(BookAppDAO.class);
//				  Book b=new Book("test","kuch bhi",100,900);int id=1;
//				  Mockito.when(mockbook.findById(id)).thenReturn(b);
//				  sv.setBk(mockbook);
//				  //Act
//				  sv.removeBook(id);
//				  //Assert
//				  Mockito.verify(mockbook).delete(id);
//			  }

	}

	@Test(expected = IllegalArgumentException.class)
	public void removeBook_doesnot_removebook_if_it_is_philospoher() {
		BookServiceImpl sv = new BookServiceImpl();
		Book b = new Book("test", "philosopher", 10000, 900);
		int id = 1;
		b.setId(id);
		BookDAO mockbook = Mockito.mock(BookDAO.class);
		PublisherDAO mockDAO = Mockito.mock(PublisherDAO.class);
		Book bb = mockbook.findById(id);
		Mockito.when(mockbook.findById(id)).thenReturn(b);
		sv.setB(mockbook);
		sv.setPubDAO(mockDAO);

		sv.removeBook(id);

	}
}
