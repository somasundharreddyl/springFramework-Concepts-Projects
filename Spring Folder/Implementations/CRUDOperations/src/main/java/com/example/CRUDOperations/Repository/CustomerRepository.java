package com.example.CRUDOperations.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CRUDOperations.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByName(String name);

}
