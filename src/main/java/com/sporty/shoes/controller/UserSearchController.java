package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.User;
import com.sporty.shoes.service.UserService;

@RestController
public class UserSearchController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User>getAllUsers(@RequestHeader("token") String token) throws MyAuthException, MyException{
		return service.getAllUsers(token);
	}
	
	@GetMapping("/users/age/{age}")
	public List<User>getAllUsersByAge(@PathVariable int age, @RequestHeader("token") String token) throws MyAuthException, MyException{
		return service.getAllUsersByAge(age, token);
	}
	
	@GetMapping("/users/name/{name}")
	public List<User>getAllUsersByName(@PathVariable String name, @RequestHeader("token") String token) throws MyAuthException, MyException{
		return service.getAllUsersByName(name, token);
	}
	
}
