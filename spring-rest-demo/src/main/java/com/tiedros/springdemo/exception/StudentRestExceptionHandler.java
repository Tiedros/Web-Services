package com.tiedros.springdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tiedros.springdemo.rest.StudentErrorResponse;

@ControllerAdvice
public class StudentRestExceptionHandler {

	// add exception handling code 
	
	// add an exception handler using @ExceptionHandler
	
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
			
			// create a student error response
			
			StudentErrorResponse error=new StudentErrorResponse();
			
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			// return response
			
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
			
		}
		
		// add another exception handler to handle any exception (catch all)
		
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
			// create a student error response
			
					StudentErrorResponse error=new StudentErrorResponse();
					
					error.setStatus(HttpStatus.BAD_REQUEST.value());
					error.setMessage(exc.getMessage());
					error.setTimeStamp(System.currentTimeMillis());
					
					// return response
					
					return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
	
}
