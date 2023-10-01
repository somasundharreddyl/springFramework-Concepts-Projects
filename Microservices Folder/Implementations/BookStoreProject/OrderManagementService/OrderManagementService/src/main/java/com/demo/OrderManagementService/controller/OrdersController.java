package com.demo.OrderManagementService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.OrderManagementService.dto.BooksDto;
import com.demo.OrderManagementService.dto.OrderDetailsDto;
import com.demo.OrderManagementService.dto.OrdersDto;
import com.demo.OrderManagementService.entity.Orders;
import com.demo.OrderManagementService.service.OrdersService;

@RestController
@RequestMapping
public class OrdersController {
	
	
	private OrdersService ordersService;
	
	public OrdersController(OrdersService ordersService) {
		super();
		this.ordersService = ordersService;
	}


	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> getAllOrders(){
		return new ResponseEntity<>(ordersService.getAllOrders(),HttpStatus.OK);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderDetailsDto> getOrderById(@PathVariable Long id){
		return new ResponseEntity<>(ordersService.getOrderById(id),HttpStatus.OK);
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Orders> placeOrder(@RequestBody OrdersDto ordersDto){
		return new ResponseEntity<>(ordersService.placeOrder(ordersDto),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<String> cancelOrder(@PathVariable Long id ){
		return new ResponseEntity<>(ordersService.cancelOrder(id),HttpStatus.OK);
	}
	
	
	@GetMapping("/orderBooks/{id}")
	public ResponseEntity<BooksDto> getBookDetails(@PathVariable Long id){
		return new ResponseEntity<>(ordersService.getBookDetails(id),HttpStatus.OK);
	}
	
}
