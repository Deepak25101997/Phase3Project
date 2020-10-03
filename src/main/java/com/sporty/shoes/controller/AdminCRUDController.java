package com.sporty.shoes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.model.Admin;
import com.sporty.shoes.service.AdminService;

@RestController
public class AdminCRUDController {

	@Autowired
	AdminService service;
	
	@PostMapping("/admin")
	public List<Admin>createAdmin(@RequestBody List<Admin> admin){
		return service.createAdmin(admin);
	}
	
	@PutMapping("/admin")
	public Admin updateAdmin(Admin admin) {
		return service.updateAdmin(admin);
	}
	
	@GetMapping("/admin/{id}")
	public Admin getAdminById(@PathVariable int id) {
		return service.getAdminById(id);
	}
	
	@DeleteMapping("/admin/{id}")
	public void deleteAdminById(@PathVariable int id) {
		service.deleteAdminById(id);
	}
	
	@PutMapping("/admin/changePassword/{id}")
	public void changeAdminPassword(@RequestBody Map<String, String> json, @PathVariable int id) {
		service.changeAdminPassword(id,json.get("sentOldPassword"),json.get("newPassword"));
	}
	
	
}
