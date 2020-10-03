package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.model.Admin;

public interface AdminService {
	
	public List<Admin> createAdmin(List<Admin> admin);
	public Admin updateAdmin(Admin admin);
	public Admin getAdminById(int id);
	public void deleteAdminById(int id);
	public Admin getAdminByUsername(String name);
	
	public void changeAdminPassword(int id,String oldPassword, String newPassword);
//	public String changeAdminPassword(int id,String oldPassword, String newPassword);
	public List<Admin> getAllAdmins();
//	public void getAllAdminsByName();


}
