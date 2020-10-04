package com.sporty.shoes.service;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;

public interface AuthenticationService {

	public String createToken(String uname,String pass) throws MyAuthException,MyException;
	public boolean validateToken(String token) throws MyAuthException;
	
}
