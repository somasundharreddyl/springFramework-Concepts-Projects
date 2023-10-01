package com.demo.OrderManagementService.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String,String>> handleMethodArgument(MethodArgumentNotValidException ex){
	Map<String,String> errorMap=new HashMap<>();
	ex.getBindingResult().getFieldErrors().forEach(error->{errorMap.put(error.getField(), error.getDefaultMessage());});
	return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleOrderNotFound(OrderNotFoundException ex){
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("error ", ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
