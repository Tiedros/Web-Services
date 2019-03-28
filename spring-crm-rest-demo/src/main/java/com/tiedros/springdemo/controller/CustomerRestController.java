package com.tiedros.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiedros.springdemo.entity.Customer;
import com.tiedros.springdemo.exception.CustomerNotFoundException;
import com.tiedros.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	// Autowired the customerService
	@Autowired
	private CustomerService customerService;
	
	// add mapping to get all  customers
	@RequestMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	// add mapping to get a single customer /customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		 Customer theCustomer= customerService.getCustomer(customerId);
		
		 if(theCustomer == null) {
			 throw new CustomerNotFoundException("Customer id not found - "+ customerId);
		 }
		 return theCustomer;
	}
	
	// add mapping for POST /customers  ... to add new customer
	
		@PostMapping("/customers")
		public Customer addCustomer(@RequestBody Customer theCustomer) {
			
			// also just in case the pass id in the JSON , make it to zero
			// this is to force save instead of update
			theCustomer.setId(0);
			customerService.saveCustomer(theCustomer);
			return theCustomer;
		}
		
	
		// add mapping for Put /customers  ... to update and existing  customer
		
			@PutMapping("/customers")
			public Customer updateCustomer(@RequestBody Customer theCustomer) {
				
				
				customerService.saveCustomer(theCustomer);
				return theCustomer;
			}
			
			// add mapping to delete a single customer /customers/{customerId}
			@DeleteMapping("/customers/{customerId}")
			public String deleteCustomer(@PathVariable int customerId) {
				 Customer theCustomer= customerService.getCustomer(customerId);
				
				 if(theCustomer == null) {
					 throw new CustomerNotFoundException("Customer id not found - "+ customerId);
				 }
				 customerService.deleteCustomer(customerId);
				 return "Deleted customer  id - "+theCustomer.getId();
			}
}









