package com.sporty.shoes.service;

import com.sporty.shoes.exceptionHandler.MyAuthException;

public interface AuthenticationService {

	public String createToken(String uname,String pass) throws MyAuthException;
	public boolean validateToken(String token) throws MyAuthException;
	
}
