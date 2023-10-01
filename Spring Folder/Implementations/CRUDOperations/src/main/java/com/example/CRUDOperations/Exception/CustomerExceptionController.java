package com.example.CRUDOperations.Exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionController {


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Object> handleValidationArguments(MethodArgumentNotValidException ex){
	Map<String,String> errorMap=new HashMap<>();
	ex.getBindingResult().getAllErrors().forEach(error->{
		
		errorMap.put(((FieldError)error).getField(),
		error.getDefaultMessage());
	
	});
	
	return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(CustomerNotFoundException.class)
public ResponseEntity<Map<String,String>> handleCustomerNotFoundException(CustomerNotFoundException ex){
	Map<String,String> errorMap=new HashMap<>();
	errorMap.put("errorMessage", ex.getMessage());
 return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.NOT_FOUND);	
}


}
