package com.tiedros.rest.webservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static  org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiedros.rest.webservices.dao.UserDaoService;
import com.tiedros.rest.webservices.entity.User;
import com.tiedros.rest.webservices.exception.UserNotFoundException;

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
	public Resource<User> getUser(@PathVariable Integer id) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id- "+ id);
		}
		
		//Hateoas
		Resource<User> resource= new Resource<User>(user);
		 ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		resource.add(linkTo.withRel("all-users"));
		 return resource;
	}
	
	// delete single user "/users/{id}"
		@RequestMapping(method=RequestMethod.DELETE,path="/users/{id}")
		public void deleteUser(@PathVariable Integer id) {
			User user = userDaoService.deleteById(id);
			if(user == null) {
				throw new UserNotFoundException("id- "+ id);
			}
			
		}
	// save user "/users"
	@RequestMapping(method=RequestMethod.POST,path="/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		
		User savedUser = userDaoService.save(user);
		
		URI location=ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		return  ResponseEntity.created(location).build();
		
	}
}
