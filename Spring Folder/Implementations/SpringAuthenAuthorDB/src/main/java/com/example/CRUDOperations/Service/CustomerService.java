package com.example.CRUDOperations.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.example.CRUDOperations.Entity.Customer;
import com.example.CRUDOperations.Exception.CustomerNotFoundException;
import com.example.CRUDOperations.Repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	
	private CustomerRepository repo;

	@Autowired
	public CustomerService(CustomerRepository repo) {
		super();
		this.repo = repo;
	}

	public Customer addCustomer(Customer customer) {
		log.info("Entered CustomerService - Save()");
				return repo.save(customer);
				
	}

	public Customer findById(Integer id) {
		log.info("Entered CustomerService - FindById()");
		if(!repo.findById(id).isPresent()) {
			log.error("Customer With Id {} Not Found",id);
			throw new CustomerNotFoundException("Customer Not Found With Id : "+id);
		}
		return repo.findById(id).get();
	}

	public List<Customer> findByName(String name) {
		log.info("Entered CustomerService - findByName()");
		// TODO Auto-generated method stub
		return repo.findByName(name);
	}

	public List<Customer> findAll() {
		log.info("Entered CustomerService - findAll()");
		return repo.findAll();
	}

	public Customer updateCustomer(Customer customer) {
		log.info("Entered CustomerService - Update Method");
		return repo.save(customer);
	}

	public String deleteCustomer(Integer id) {
		log.info("Entered CustomerService - delete()");
		if(!repo.findById(id).isPresent()) {
			log.error("Customer With Id {} Not Found",id);
			throw new CustomerNotFoundException("Customer Not Found With Id : "+id);
		}
		repo.deleteById(id);
		return "Customer Deleted Successfully";
	}
	
	

}
