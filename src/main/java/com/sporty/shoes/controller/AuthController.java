package com.sporty.shoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.service.AuthenticationService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/login")
	public String authenticate(@RequestHeader("username") String username, @RequestHeader("password") String password) throws MyAuthException {
	
		return authService.createToken(username,password);
	}

}
