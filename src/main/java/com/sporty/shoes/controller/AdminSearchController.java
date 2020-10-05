package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Admin;
import com.sporty.shoes.service.AdminService;

@RestController
public class AdminSearchController {

	@Autowired
	private AdminService service;
	
	@GetMapping("/admins")
	public List<Admin> getAllAdmins(@RequestHeader("token") String token) throws MyException, MyAuthException{
		return service.getAllAdmins(token);
	}
	
}
