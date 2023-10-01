package com.example.CRUDOperations.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDOperations.Entity.Customer;
import com.example.CRUDOperations.Service.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
   @PostMapping("/save")
	public ResponseEntity<Customer> save(@RequestBody @Valid Customer customer) {
	   log.info("Entered CustomerController - Save()");
		return new ResponseEntity<Customer>(customerService.addCustomer(customer),HttpStatus.CREATED);
	}
	
	@GetMapping("/findById/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Customer> findById(@PathVariable Integer id){
		log.info("Entered CustomerController - findById()");
		return new ResponseEntity<Customer>(customerService.findById(id),HttpStatus.OK);
	}
	
	
	@GetMapping("/findByName/{name}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<Customer>> findByName(@PathVariable String name){
		log.info("Entered CustomerController - findByName()");
		return new ResponseEntity<List<Customer>>(customerService.findByName(name),HttpStatus.OK);
	}
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Customer>> findAll(){
		log.info("Entered CustomerController - findAll()");
		return new ResponseEntity<List<Customer>> (customerService.findAll(),HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Customer> update(@RequestBody @Valid Customer customer){
		log.info("Entered CustomerController - update()");
		return new ResponseEntity<Customer> (customerService.updateCustomer(customer),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		log.info("Entered CustomerController - delete()");
		return new ResponseEntity<String>(customerService.deleteCustomer(id),HttpStatus.OK);
	}
	
	
	
}
