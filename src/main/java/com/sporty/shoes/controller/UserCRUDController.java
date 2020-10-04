package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.User;
import com.sporty.shoes.service.UserService;

@RestController
public class UserCRUDController {

	@Autowired
	private UserService service;

	@PostMapping("/user")
	public List<User> createUser(@RequestBody List<User> user, @RequestHeader("token") String token)
			throws MyException, MyAuthException {
		return service.createUser(user, token);
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user, @RequestHeader("token") String token)
			throws MyAuthException, MyException {
		return service.updateUser(user, token);
	}

	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id, @RequestHeader("token") String token)
			throws MyException, MyAuthException {
		return service.getUserById(id, token);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable int id, @RequestHeader("token") String token)
			throws MyException, MyAuthException {
		service.deleteUserById(id, token);
	}

}
