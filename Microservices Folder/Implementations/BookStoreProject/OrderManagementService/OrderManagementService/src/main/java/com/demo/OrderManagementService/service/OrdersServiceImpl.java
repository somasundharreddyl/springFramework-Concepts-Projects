package com.demo.OrderManagementService.service;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.OrderManagementService.dto.BooksDto;
import com.demo.OrderManagementService.dto.OrderDetailsDto;
import com.demo.OrderManagementService.dto.OrdersDto;
import com.demo.OrderManagementService.entity.Orders;
import com.demo.OrderManagementService.exception.OrderNotFoundException;
import com.demo.OrderManagementService.repository.OrdersRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class OrdersServiceImpl implements OrdersService {
	
	private OrdersRepository ordersRepository;
	
	//private RestTemplateBuilder restTemplateBuilder;
	
	private RestTemplate restTemplate;

	public OrdersServiceImpl(OrdersRepository ordersRepository,RestTemplate restTemplate) {
		super();
		this.ordersRepository = ordersRepository;
		this.restTemplate=restTemplate;
	}

	
	@Override
	public List<Orders> getAllOrders() {
		return ordersRepository.findAll();
	}
	
	
	@Override
	public OrderDetailsDto getOrderById(Long id) {
		if(ordersRepository.findById(id).isEmpty()) {
			log.error("Order with Id: {} Not Found",id);
		   throw new OrderNotFoundException("Order with Id: "+id+" not Found");
		}
		Orders orders=ordersRepository.findById(id).get();
		BooksDto booksDto=getBookDetails(orders.getBookId());
		OrderDetailsDto orderDetailsDto=new OrderDetailsDto();
		orderDetailsDto.setAuthor(booksDto.getAuthor());
		orderDetailsDto.setBookId(booksDto.getId());
		orderDetailsDto.setCustomerName(orders.getCustomerName());
		orderDetailsDto.setId(id);
		orderDetailsDto.setOrderStatus(orders.getOrderStatus());
		orderDetailsDto.setPrice(booksDto.getPrice());
		orderDetailsDto.setTitle(booksDto.getTitle());
	
		return orderDetailsDto;
	}
	

	@Override
	public Orders placeOrder(OrdersDto ordersDto) {
		Orders orders=new Orders(0L,ordersDto.getCustomerName(),ordersDto.getOrderStatus(),ordersDto.getBookId());
		return ordersRepository.save(orders);
	}
	

	@Override
	public String cancelOrder(Long id) {
		if(ordersRepository.findById(id).isEmpty()) {
			log.error("Order with Id: {} Not Found",id);
			    throw new OrderNotFoundException("Order with Id: "+id+" not Found");
			}
		ordersRepository.deleteById(id);
		return "Order with Id: "+id+" SuccessFully Deleted";
	  }


	@Override
	public BooksDto getBookDetails(Long id) {
		
		String url="http://BooksService/books/"+id;
		
		return restTemplate.getForObject(url, BooksDto.class);
	}
	
}
