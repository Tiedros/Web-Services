package com.tiedros.rest.webservices.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
public class HelloWorldController {

	// GET 
	// URI - /hello-world
	// method helloWorld
	@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	// mapping to return a bean
	
	@RequestMapping(method=RequestMethod.GET,path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		
		return new HelloWorldBean("Hello World");
	}
	

	@RequestMapping(method=RequestMethod.GET,path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello World, %s",name));
	}
}
