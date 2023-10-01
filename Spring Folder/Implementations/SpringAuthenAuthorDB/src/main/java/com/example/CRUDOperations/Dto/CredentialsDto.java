package com.example.CRUDOperations.Dto;

import lombok.Data;

@Data
public class CredentialsDto {
	private String username;
	private String password;
	private String email;
	private String roles;
}
