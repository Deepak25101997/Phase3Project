package com.sporty.shoes.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.model.Admin;
import com.sporty.shoes.model.MyToken;
import com.sporty.shoes.repository.MyTokenRepository;
import com.sporty.shoes.service.AdminService;
import com.sporty.shoes.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private MyTokenRepository repo;
	
	@Autowired
	private AdminService adminService;

	@Override
	public String createToken(String uname, String pass) throws MyAuthException {

		Admin admin = null;
		try {
			if (uname == null || pass == null)
				throw new MyAuthException("Username or password cannot be NULL !");
			admin = adminService.getAdminByUsername(uname);

			if (admin == null) {
				throw new MyAuthException("Admin with the given username not found !");
			}

			String savedPassword = admin.getPassword();
			if (savedPassword.equals(pass)) {
				String myGeneratedToken = UUID.randomUUID().toString();
				
				MyToken tempToken = new MyToken();
				tempToken.setTokenValue(myGeneratedToken);
				repo.save(tempToken);
				
				return myGeneratedToken;
			} else {
				throw new MyAuthException("Invalid Credentials !");
			}
		} catch (Exception e) {
			throw new MyAuthException("Invalid Credentials !");
		}
	}

	@Override
	public boolean validateToken(String token) throws MyAuthException {
		
		
		try {
			if(token==null) throw new MyAuthException("Invalid Token, Access Denied!!");
			
			MyToken myToken = repo.findByTokenValue(token);
			
			if(myToken==null) throw new MyAuthException("Invalid Token, Access Denied!!");
			
			return true;
			
		} catch (Exception e) {
			throw new MyAuthException("Invalid Token, Access Denied!!");
		}
		
	}

}
