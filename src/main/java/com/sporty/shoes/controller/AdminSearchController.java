package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.model.Admin;
import com.sporty.shoes.service.AdminService;

@RestController
public class AdminSearchController {

	@Autowired
	AdminService service;
	
	@GetMapping("/admins")
	public List<Admin>getAllAdmins(){
		return service.getAllAdmins();
	}
	
}
