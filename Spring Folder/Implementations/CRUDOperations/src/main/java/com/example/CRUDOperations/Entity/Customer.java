package com.example.CRUDOperations.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@NotNull(message="Id Should not be Null")
private Integer id;
	@NotBlank(message="Name Should not be Blank")
private String name;
    @Email(message="Enter Correct Email Id")
private String email;
}
