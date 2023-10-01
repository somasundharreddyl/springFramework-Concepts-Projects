package com.demo.OrderManagementService.dto;

import com.demo.OrderManagementService.enums.OrderStatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdersDto {
	
	@NotBlank
	private String customerName;
	@NotNull
	private Long bookId;
	@NotBlank
	private OrderStatusEnum orderStatus;

}
