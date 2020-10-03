package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.model.User;
import com.sporty.shoes.service.UserService;

@RestController
public class UserCRUDController {

	@Autowired
	private UserService service;
	
	@PostMapping("/user")
	public List<User> createUser(@RequestBody List<User> user){
		return service.createUser(user);
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id){
		return service.getUserById(id);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable int id) {
		service.deleteUserById(id);
	}
	
}
