package com.demo.OrderManagementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.OrderManagementService.dto.OrdersDto;
import com.demo.OrderManagementService.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

	

}
