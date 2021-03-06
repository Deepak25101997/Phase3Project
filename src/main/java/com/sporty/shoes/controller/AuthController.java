package com.sporty.shoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.service.AuthenticationService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/login")
	public String authenticate(@RequestHeader("username") String username, @RequestHeader("password") String password) throws MyAuthException, MyException {
	
		return authService.createToken(username,password);
	}

	@PostMapping("/logout")
	public String deAuthenticate(@RequestHeader("token") String token) throws MyAuthException {
		return authService.logout(token);
	}
	
	@GetMapping("/welcome")
	public String welcomeMessage() {
		return "Hello Deepak";
	}
	
}
