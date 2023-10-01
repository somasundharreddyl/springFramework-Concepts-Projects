/**
 * 
 */
package com.example.CRUDOperations;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.CRUDOperations.Controller.CustomerController;
import com.example.CRUDOperations.Entity.Customer;
import com.example.CRUDOperations.Service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;
	List<Customer> customersList=new ArrayList<>();
	Customer customer1;
	Customer customer2;
	

	
	@BeforeEach
	void setUp() throws Exception {
		customer1=new Customer(1,"Ravi","ravi@gmail.com");
		customer2=new Customer(2,"Pallavi","pallavi@gmail.com");
		customersList.add(customer1);
		customersList.add(customer2);
	}

	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindById() throws Exception {
		when(customerService.findById(1)).thenReturn(customer1);
		this.mockMvc.perform(get("/customers/findById/1"))
		    .andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void testSave() throws Exception {
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow=mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(customer1);
		
		
		when(customerService.addCustomer(customer1)).thenReturn(customer1);
		
		this.mockMvc.perform(post("/customers/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
		        .andDo(print()).andExpect(status().isCreated());
	}
	
	@Test
	void testFindByName() throws Exception{
		when(customerService.findByName(null)).thenReturn(customersList);
		this.mockMvc.perform(get("/customers/findByName/Pallavi"))
		              .andDo(print()).andExpect(status().isOk());
	}
	
	
	@Test
	void testFindAll() throws Exception {
		when(customerService.findAll()).thenReturn(customersList);
		this.mockMvc.perform(get("/customers/findAll"))
		            .andDo(print()).andExpect(status().isOk());
	}
	
	
	@Test
	void testUpdateCustomer() throws Exception{
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow=mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(customer1);
		
		when(customerService.updateCustomer(customer2)).thenReturn(customer2);
		this.mockMvc.perform(put("/customers/update")
				    .contentType(MediaType.APPLICATION_JSON)
			        .content(requestJson))
		            .andDo(print()).andExpect(status().isOk());
	}
	
	
	@Test
	void testDeleteCustomer() throws Exception{
		when(customerService.deleteCustomer(1)).thenReturn("Customer Deleted Successfully");
		this.mockMvc.perform(delete("/customers/delete/1"))
		            .andDo(print()).andExpect(status().isOk());
	}
	

}
