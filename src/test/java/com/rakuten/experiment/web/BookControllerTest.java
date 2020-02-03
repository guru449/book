package com.rakuten.experiment.web;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.experiment.domain.Book;
import com.rakuten.experiment.domain.Publisher;
import com.rakuten.experiment.service.BookService;
import com.rakuten.experiment.service.PublisherService;

@RunWith(SpringRunner.class)
@WebMvcTest({BookController.class}) 
public class BookControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
	@MockBean
	BookService bookService;
	@MockBean
	PublisherService service;

	@Test
	public void getBookById_Returns_Ok_WithValue_If_Book_Exists() throws Exception {
		// Arrange
		Book found = new Book("test", "test",1,1);
		int id = 1;
		found.setId(id);
		Mockito.when(bookService.findById(id)).thenReturn(found);
		// Act//Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}",id)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
		Mockito.verify(bookService).findById(id);
	}
	@Test
	public void getBookById_Returns_NotFound_If_Book_Does_Not_Exists() throws Exception {
		// Arrange
		int id = 1;
		//Book b=null;
		Mockito.when(bookService.findById(id)).thenReturn(null);
		// Act//Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}",id)).andExpect(MockMvcResultMatchers.status().isNotFound());
		Mockito.verify(bookService).findById(id);
	}
	@Test
	public void addNewBook_Returns_Created_If_Book_Is_Created() throws Exception {
		// Arrange
		Book toBeAdded = new Book("test", "test",123,123);
		Publisher p = new Publisher("test","test");
		int id = 1;
		int publisherId =1;
		p.setId(publisherId);
		toBeAdded.setId(id);
		toBeAdded.setPublisher(p);
		ObjectMapper mapper = new ObjectMapper();
		Mockito.when(bookService.addBook(Mockito.any(Book.class),Mockito.eq(publisherId)))
		.thenReturn(id);
		// Act//Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/publisher/{publisherId}/books",publisherId)
		.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(toBeAdded)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.header()
				.string(HttpHeaders.LOCATION, "/publisher/"+publisherId+"/books/"+id));
		Mockito.verify(bookService).addBook(Mockito.any(Book.class),Mockito.eq(publisherId));
	}
	@Test
	public void addNewBook_Returns_BadRequest_If_Textbook_With_Pages_GT_Thousand() throws Exception {
		// Arrange
		Book toBeAdded = new Book("test", "test",123,123);
		Publisher p = new Publisher("test","test");
		int id = 1;
		int publisherId =1;
		p.setId(publisherId);
		toBeAdded.setId(id);
		toBeAdded.setPublisher(p);
		ObjectMapper mapper = new ObjectMapper();
		Mockito.when(bookService.addBook(Mockito.any(Book.class),Mockito.eq(publisherId))).thenThrow(new IllegalArgumentException());
		// Act//Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/publisher/{publisherId}/books",publisherId).contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(toBeAdded))).andExpect(MockMvcResultMatchers.status().isBadRequest());
		Mockito.verify(bookService).addBook(Mockito.any(Book.class),Mockito.eq(publisherId));
	}
	@Test
	public void addNewBook_Returns_NotFound_If_Publisher_Does_Not_exist() throws Exception {
		// Arrange
		Book toBeAdded = new Book("test", "test",1234,123);
		int id = 1;
		int publisherId =1;
		ObjectMapper mapper = new ObjectMapper();

		Mockito.when(bookService.addBook(Mockito.any(Book.class),Mockito.eq(publisherId))).thenThrow(new IllegalStateException());
		// Act//Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/publisher/{publisherId}/books",publisherId).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(toBeAdded))).andExpect(MockMvcResultMatchers.status().isNotFound());
		Mockito.verify(bookService).addBook(Mockito.any(Book.class),Mockito.eq(publisherId));
	}
	
	
}