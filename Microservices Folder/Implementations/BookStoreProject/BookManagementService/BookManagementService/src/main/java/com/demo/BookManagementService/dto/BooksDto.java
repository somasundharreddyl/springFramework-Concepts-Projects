package com.demo.BookManagementService.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BooksDto {
	@NotEmpty
	private String title;
    @NotBlank
   private String author;
   @Max(1000)
   @Min(150)
   private Double price;
}
