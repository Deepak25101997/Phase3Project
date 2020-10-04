package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Admin;

public interface AdminService {

	public List<Admin> createAdmin(List<Admin> admin) throws MyException;

	public Admin updateAdmin(Admin admin, String token) throws MyException, MyAuthException;

	public Admin getAdminById(int id, String token) throws MyException, MyAuthException;

	public void deleteAdminById(int id, String token) throws MyException, MyAuthException;

	public Admin getAdminByUsername(String name) throws MyException;

	public void changeAdminPassword(int id, String oldPassword, String newPassword, String token)
			throws MyException, MyAuthException;

	public List<Admin> getAllAdmins(String token) throws MyException, MyAuthException;

}
