package com.demo.BookManagementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

public class BookManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementServiceApplication.class, args);
	}

}
