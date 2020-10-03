package com.sporty.shoes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.model.Admin;
import com.sporty.shoes.repository.AdminRepository;
import com.sporty.shoes.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository repo;
	
	@Override
	public List<Admin> createAdmin(List<Admin>  admin) {
		return repo.saveAll(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		return repo.save(admin);
	}

	@Override
	public Admin getAdminById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteAdminById(int id) {
		repo.deleteById(id);
	}
	
	@Override
	public Admin getAdminByUsername(String name) {
		return repo.findByUsername(name);
	}

	@Override
	public void changeAdminPassword(int id, String sentOldPassword, String newPassword) {
		String savedOldPass = repo.findPasswordById(id);
		if(savedOldPass.equals(sentOldPassword)) {
			repo.updatePassword(newPassword,id);
		}
		//exception
	}
	
//	@Override
//	public String changeAdminPassword(int id, String sentOldPassword, String newPassword) {
//		String savedOldPass = repo.findPasswordById(id);
//		if(sentOldPassword.equals(savedOldPass))return "true";
//		else return "False";
//	}

	@Override
	public List<Admin> getAllAdmins() {
		return repo.findAll();
	}


}
