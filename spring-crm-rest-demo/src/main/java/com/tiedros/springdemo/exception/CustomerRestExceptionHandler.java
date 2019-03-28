package com.tiedros.springdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tiedros.springdemo.entity.Customer;
import com.tiedros.springdemo.entity.CustomerErrorResponse;
import com.tiedros.springdemo.service.CustomerService;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// add an exception handler to handle CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
		// create customer response entity
		CustomerErrorResponse error= new CustomerErrorResponse(
										HttpStatus.NOT_FOUND.value(),
										exc.getMessage(),
										System.currentTimeMillis()
										);
		
		
		
		// return response entity
		return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	// add another exception handler .... to catch any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc){
		// create customer response entity
		CustomerErrorResponse error= new CustomerErrorResponse(
										HttpStatus.BAD_REQUEST.value(),
										exc.getMessage(),
										System.currentTimeMillis()
										);
		
		
		
		// return response entity
		return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
}










