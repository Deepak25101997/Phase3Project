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
import com.sporty.shoes.model.Admin;
import com.sporty.shoes.model.ChangePasswordRequest;
import com.sporty.shoes.service.AdminService;

@RestController
public class AdminCRUDController {

	@Autowired
	AdminService service;

	@PostMapping("/admin")
	public List<Admin> createAdmin(@RequestBody List<Admin> admin) throws MyException {
		return service.createAdmin(admin);
	}

	@PutMapping("/admin")
	public Admin updateAdmin(Admin admin, @RequestHeader("token") String token) throws MyException, MyAuthException {
		return service.updateAdmin(admin, token);
	}

	@GetMapping("/admin/{id}")
	public Admin getAdminById(@PathVariable int id, @RequestHeader("token") String token)
			throws MyException, MyAuthException {
		return service.getAdminById(id, token);
	}

	@DeleteMapping("/admin/{id}")
	public void deleteAdminById(@PathVariable int id, @RequestHeader("token") String token)
			throws MyAuthException, MyException {
		service.deleteAdminById(id, token);
	}

//	@PutMapping("/admin/changePassword/{id}")
//	public void changeAdminPassword(@RequestBody Map<String, String> json, @PathVariable int id,
//			@RequestHeader("token") String token) throws MyException, MyAuthException {
//		service.changeAdminPassword(id, json.get("sentOldPassword"), json.get("newPassword"), token);
//	}

	@PutMapping("/admin/changePassword/{id}")
	public String changeAdminPassword(@RequestBody ChangePasswordRequest changePassReq, @PathVariable int id,
			@RequestHeader("token") String token) throws MyException, MyAuthException {
		return service.changeAdminPassword(id, changePassReq, token);
	}
	
}
