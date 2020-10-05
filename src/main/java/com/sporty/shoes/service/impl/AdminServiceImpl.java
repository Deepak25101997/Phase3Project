package com.sporty.shoes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Admin;
import com.sporty.shoes.model.ChangePasswordRequest;
import com.sporty.shoes.repository.AdminRepository;
import com.sporty.shoes.repository.MyTokenRepository;
import com.sporty.shoes.service.AdminService;
import com.sporty.shoes.service.AuthenticationService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;

	@Autowired
	private AuthenticationService AuthService;

	@Autowired
	private MyTokenRepository myTokenRepo;

	@Override
	public List<Admin> createAdmin(List<Admin> admin) throws MyException {

		List<Admin> admins = new ArrayList<>();

		for (Admin ad : admin) {
			if (ad.getName() == null || ad.getEmail() == null || ad.getContactNo() <= 0 || ad.getPassword() == null
					|| ad.getUsername() == null)
				throw new MyException("Invalid data passed !!");
		}

		try {
			if (admin.size() == 0)
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

			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				if (admin.getContactNo() <= 0 || admin.getEmail() == null || admin.getName() == null
						|| admin.getPassword() == null || admin.getUsername() == null)
					throw new MyException("Admin Data missing. Please pass some data to be inserted !!");
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

			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No admin found with id" + id);
		}

		return admin;
	}

	@Override
	public String deleteAdminById(int id, String token) throws MyException, MyAuthException {

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				@SuppressWarnings("unused")
				Admin admin = repo.findById(id).get();
				repo.deleteById(id);
				myTokenRepo.deleteByTokenValue(token);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No admin exists with id " + id);
		}

		return "Admin Deleted Successfully !! Kindly Login Again for security purpose !";
	}

	@Override
	public Admin getAdminByUsername(String name) throws MyException {

		Admin admin = null;

		try {

			if (name == null)
				throw new MyException("Name cannot be null !!");

			admin = repo.findByUsername(name);

		} catch (MyException e) {
			throw new MyException(e.getMessage());
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No admin found with username " + name);
		}

		return admin;
	}

	@Override
	public String changeAdminPassword(int id, ChangePasswordRequest changePassReq, String token)
			throws MyException, MyAuthException {

		try {

			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0)
					throw new MyException("Id cannot be 0 or negative !!");

				if (changePassReq.getNewPassword() == null || changePassReq.getOldPassword() == null)
					throw new MyException("Please send oldPassword and newPassword in the body!!");

				String savedOldPass = repo.findPasswordById(id);

				if (savedOldPass.equals(changePassReq.getOldPassword())) {

					repo.updatePassword(changePassReq.getNewPassword(), id);

					// deleting the present token and requiring admin to login again with new
					// password for better security
					AuthService.deleteByTokenValue(token);
				} else {
					throw new MyException("Oldpassword entered is not matching with the saved one !!");
				}
			}

		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No admin found with given id !!");
		}

		return "Password Changed Successfully. Please LOGIN again using new password !!";
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
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No admins found !!!");
		}

		return admins;
	}

}
