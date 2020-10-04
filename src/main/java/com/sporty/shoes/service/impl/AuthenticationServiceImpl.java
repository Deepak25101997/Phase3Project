package com.sporty.shoes.service.impl;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
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
	public String createToken(String uname, String pass) throws MyAuthException, MyException {

		Admin admin = null;
		
		String myGeneratedToken = UUID.randomUUID().toString();
		
		try {
			if (uname == null || pass == null)
				throw new MyAuthException("Username or password cannot be NULL !");
			admin = adminService.getAdminByUsername(uname);

			String savedPassword = admin.getPassword();
			if (savedPassword.equals(pass)) {
				
				MyToken tempToken = new MyToken();
				tempToken.setTokenValue(myGeneratedToken);
				repo.save(tempToken);
			} else {
				throw new MyAuthException("Invalid Credentials !");
			}
		} catch (MyAuthException e) {
			throw new MyAuthException("Invalid Credentials !");
		}catch (NoSuchElementException e) {
			throw new NoSuchElementException("Admin with the given username not found !");
		}catch (MyException e) {
			throw new MyException(e.getMessage());
		}
		
		return myGeneratedToken;
	}

	@Override
	public boolean validateToken(String token) throws MyAuthException {
		
		
		try {
			if(token==null) throw new MyAuthException("Invalid Token, Access Denied !!");
			
			MyToken myToken = repo.findByTokenValue(token);
			
			if(myToken==null) throw new MyAuthException("Invalid Token, Access Denied !!");
			
			return true;
			
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		}
		
	}

}
