package com.demo.OrderManagementService.service;

import java.util.List;

import com.demo.OrderManagementService.dto.BooksDto;
import com.demo.OrderManagementService.dto.OrderDetailsDto;
import com.demo.OrderManagementService.dto.OrdersDto;
import com.demo.OrderManagementService.entity.Orders;

public interface OrdersService {

	List<Orders> getAllOrders();

	OrderDetailsDto getOrderById(Long id);

	Orders placeOrder(OrdersDto ordersDto);

	String cancelOrder(Long id);

	BooksDto getBookDetails(Long id);
}
