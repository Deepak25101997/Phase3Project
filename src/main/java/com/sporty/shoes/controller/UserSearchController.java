package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.model.User;
import com.sporty.shoes.service.UserService;

@RestController
public class UserSearchController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User>getAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/users/age/{age}")
	public List<User>getAllUsersByAge(@PathVariable int age){
		return service.getAllUsersByAge(age);
	}
	
	@GetMapping("/users/name/{name}")
	public List<User>getAllUsersByName(@PathVariable String name){
		return service.getAllUsersByName(name);
	}
	
}
