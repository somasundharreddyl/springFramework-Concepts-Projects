package com.example.CRUDOperations;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.CRUDOperations.Entity.Customer;
import com.example.CRUDOperations.Repository.CustomerRepository;

@DataJpaTest
class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepo;
	Customer customer;

	@BeforeEach
	void setUp() throws Exception {
		customer=new Customer(1,"ravi","ravi@gmail.com");
		customerRepo.save(customer);
	}

	@AfterEach
	void tearDown() throws Exception {
		customer=null;
		customerRepo.deleteAll();
	}

	@Test
	void testFundByName_Found() {
		List<Customer> customersList=customerRepo.findByName("ravi");
		assertThat(customersList.get(0).getId()).isEqualTo(customer.getId());                              //assertThat from assertionJ
		assertThat(customersList.get(0).getEmail()).isEqualTo(customer.getEmail());
	}
	
	@Test
	void testFindByName_NotFound() {
		assertThat(customerRepo.findByName("kumar").isEmpty()).isTrue();
	}

}
