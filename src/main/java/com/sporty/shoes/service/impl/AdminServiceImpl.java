package com.sporty.shoes.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Admin;
import com.sporty.shoes.model.User;
import com.sporty.shoes.repository.AdminRepository;
import com.sporty.shoes.service.AdminService;
import com.sporty.shoes.service.AuthenticationService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;

	@Autowired
	private AuthenticationService AuthService;

	@Override
	public List<Admin> createAdmin(List<Admin> admin) throws MyException {

		List<Admin> admins = new ArrayList<>();

		try {
			if (admin == null)
				throw new MyException("Admin cannot be null ! Pass some admin data.");

			admins = repo.saveAll(admin);

		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return admins;
	}

	@Override
	public Admin updateAdmin(Admin admin, String token) throws MyException, MyAuthException {

		Admin tempAdmin = null;

		try {
			if (token == null)
				throw new MyAuthException("Token Missing !!");
			if (admin == null)
				throw new MyException("Admin Data missing. Please pass some data to be inserted !!");
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				tempAdmin = repo.save(admin);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return tempAdmin;
	}

	@Override
	public Admin getAdminById(int id, String token) throws MyException, MyAuthException {

		Admin admin = null;

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				admin = repo.findById(id).get();
				if (admin == null)
					throw new MyException("No admin found with id" + id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return admin;
	}

	@Override
	public void deleteAdminById(int id, String token) throws MyException, MyAuthException {

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				Admin admin = repo.findById(id).get();
				if (admin == null)
					throw new MyException("No admin exists with id " + id);
				repo.deleteById(id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

	}

	@Override
	public Admin getAdminByUsername(String name) throws MyException {

		Admin admin = null;

		try {

			if (name == null)
				throw new MyException("Name cannot be null !!");

			admin = repo.findByUsername(name);

			if (admin == null)
				throw new MyException("No admin found with username " + name);

		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return admin;
	}

	@Override
	public void changeAdminPassword(int id, String sentOldPassword, String newPassword, String token)
			throws MyException, MyAuthException {

		try {

			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				
				if(id<=0)throw new MyException("Id cannot be 0 or negative !!");
				
				if(sentOldPassword==null) throw new MyException("Old password missing !!");
				if(newPassword==null) throw new MyException("New password missing !!");
				
				String savedOldPass = repo.findPasswordById(id);
				
				if(savedOldPass==null) throw new MyException("No admin found with given id !!");
				
				if (savedOldPass.equals(sentOldPassword)) {
					repo.updatePassword(newPassword, id);
				}
				else {
					throw new MyException("Oldpassword entered is not matching with the saved one !!");
				}
			}

		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

	}

//	@Override
//	public String changeAdminPassword(int id, String sentOldPassword, String newPassword) {
//		String savedOldPass = repo.findPasswordById(id);
//		if(sentOldPassword.equals(savedOldPass))return "true";
//		else return "False";
//	}

	@Override
	public List<Admin> getAllAdmins(String token) throws MyException, MyAuthException {

		List<Admin> admins = new ArrayList<>();

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				admins = repo.findAll();
				if (admins.size() <= 0)
					throw new MyException("No admins found !!!");
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return admins;
	}

}
