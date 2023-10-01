package com.example.CRUDOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.CRUDOperations.Entity.Customer;
import com.example.CRUDOperations.Repository.CustomerRepository;
import com.example.CRUDOperations.Service.CustomerService;

class CustomerServiceTest {
	
	@Mock
	private CustomerRepository customerRepo;
	private CustomerService customerService;
	AutoCloseable autoCloseable;
	Customer customer;

	@BeforeEach
	void setUp() throws Exception {
	autoCloseable = MockitoAnnotations.openMocks(this);
	customer=new Customer(1,"Rajinikanth","rajinikanth@only.com");
	customerService=new CustomerService(customerRepo);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testAddCustomer() {
		mock(Customer.class);
		mock(CustomerRepository.class);
		
		when(customerRepo.save(customer)).thenReturn(customer);
		assertThat(customerService.addCustomer(customer).getEmail()).isEqualTo(customer.getEmail());
	}
	
	@Test
	void testFindById() {
		mock(CustomerRepository.class);
		mock(Customer.class);
		when(customerRepo.findById(1)).thenReturn(Optional.ofNullable(customer));
		assertThat(customerService.findById(1).getId()).isEqualTo(customer.getId());
	}
	
	@Test
	void testFindByName() {
	mock(Customer.class);
	mock(CustomerRepository.class);
	when(customerRepo.findByName("Rajinikanth")).thenReturn(new ArrayList<Customer>(Collections.singleton(customer)));
	assertThat(customerService.findByName("Rajinikanth").get(0).getId()).isEqualTo(customer.getId());
	}
	
	
	@Test
	void testFindAll() {
		mock(Customer.class);
		mock(CustomerRepository.class);
		when(customerRepo.findAll()).thenReturn(new ArrayList<Customer>(Collections.singleton(customer)));
		assertThat(customerService.findAll().get(0).getEmail()).isEqualTo(customer.getEmail());
	}
	
	@Test
	void testUpdateCustomer() {
		mock(Customer.class);
		mock(CustomerRepository.class);
		when(customerRepo.save(customer)).thenReturn(customer);
		assertThat(customerService.updateCustomer(customer).getEmail()).isEqualTo(customer.getEmail());
	}
	
	@Test
	void testDeleteCustomer() {
		mock(Customer.class);
		mock(CustomerRepository.class,Mockito.CALLS_REAL_METHODS);
		doAnswer(Answers.CALLS_REAL_METHODS).when(customerRepo).deleteById(1);
		assertThat(customerService.deleteCustomer(1)).isEqualTo("Customer Deleted Successfully");
	}

}
