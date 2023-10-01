package com.example.CRUDOperations.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDOperations.Dto.CredentialsDto;
import com.example.CRUDOperations.Entity.Credentials;
import com.example.CRUDOperations.Service.CredentialsService;

@RestController
@RequestMapping("/register")
public class CredentialsController {

private CredentialsService credentialsService;



public CredentialsController(CredentialsService credentialsService) {
	super();
	this.credentialsService = credentialsService;
}


@PostMapping("")
public ResponseEntity<Credentials> register(@RequestBody Credentials credentials){
return new ResponseEntity<Credentials>(credentialsService.register(credentials),HttpStatus.CREATED);	
}
	
	
}
