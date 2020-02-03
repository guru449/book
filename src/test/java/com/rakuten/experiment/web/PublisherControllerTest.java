package com.rakuten.experiment.web;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.experiment.domain.Publisher;
import com.rakuten.experiment.service.BookService;
import com.rakuten.experiment.service.PublisherService;
import com.rakuten.experiment.web.BookController;
import com.rakuten.experiment.web.PublisherController;

@RunWith(SpringRunner.class)
@WebMvcTest({PublisherController.class}) 
public class PublisherControllerTest {
	
	// Request Spring to Mock this bean for us.
	@Autowired //to make spring inject object automatically.
	MockMvc mockMvc;
	
	@MockBean
	PublisherService service;
	
	@MockBean
	BookService bev;
	@Test
	public void getPublisherById_Returns_OK_With_Correct_Publisher_If_Found() throws Exception {
		// Arrange
		Publisher found = new Publisher("Guru","Bangalore");
		int id = 1;
		found.setId(id);
		Mockito.when(service.findById(id)).thenReturn(found);
		
		//Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/publisher/{id}",id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.pubid", CoreMatchers.is(id)));
	}
	@Test
	public void getPublisherById_Returns_NotFound_If_Id_NotPresent() throws Exception {
		//Arrange
		Publisher found = new Publisher("Guru","Bangalore");
		int id = 1;
		found.setId(id);
		Mockito.when(service.findById(id)).thenReturn(null);
		
		//Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/publisher/{id}",id))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
				//.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
	}
	@Test
	public void addProduct_Returns_No_Exception() throws Exception {
		
		 // Arrange
	    Publisher added = new Publisher("guru","bangalore");
	    int id = 1;
	    added.setId(id);

	    ObjectMapper mapper = new ObjectMapper();

	    // Act & Assert
	    Mockito.when(service.addPublisher((Mockito.any(Publisher.class)))).thenReturn(id);
	    
	    mockMvc
	        .perform(MockMvcRequestBuilders.post("/publisher")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(mapper.writeValueAsString(added)))
	        .andExpect(MockMvcResultMatchers.status().isCreated())
	        .andExpect(MockMvcResultMatchers.header().string("location", "/publisher/"+ id));

	    Mockito.verify(service, Mockito.times(1)).addPublisher(Mockito.any(Publisher.class));
	}

}
