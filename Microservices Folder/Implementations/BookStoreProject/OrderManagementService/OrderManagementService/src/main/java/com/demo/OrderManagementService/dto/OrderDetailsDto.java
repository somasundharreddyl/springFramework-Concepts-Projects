package com.demo.OrderManagementService.dto;

import com.demo.OrderManagementService.enums.OrderStatusEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {

	
	private String customerName;
	private Long bookId;
	private OrderStatusEnum orderStatus;
	private Long id;
	private String title;
	private String author;
	private Double price;

	
	
}
