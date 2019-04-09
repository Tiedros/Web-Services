package com.tiedros.rest.webservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiedros.rest.webservices.dao.UserDaoService;
import com.tiedros.rest.webservices.entity.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService  userDaoService;
	// get all users "/users"
	@RequestMapping(method=RequestMethod.GET,path="/users")
	public List<User> getUsers(){
		return userDaoService.findAll();
	}
	
	// get single user "/users/{id}"
	@RequestMapping(method=RequestMethod.GET,path="/users/{id}")
	public User getUser(@PathVariable Integer id) {
		return userDaoService.findOne(id);
	}
	// save user "/users"
	@RequestMapping(method=RequestMethod.POST,path="/users")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		
		User savedUser = userDaoService.save(user);
		
		URI location=ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		return  ResponseEntity.created(location).build();
		
	}
}
