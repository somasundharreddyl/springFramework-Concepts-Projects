package com.demo.OrderManagementService.entity;

import com.demo.OrderManagementService.enums.OrderStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String customerName;
@Enumerated(EnumType.STRING)
private OrderStatusEnum orderStatus;
private Long BookId;
}
