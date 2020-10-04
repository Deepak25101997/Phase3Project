package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.User;

public interface UserService {
	
	public List<User> createUser(List<User> user, String token) throws MyException, MyAuthException;
	public User updateUser(User user, String token) throws MyAuthException,MyException;
	public User getUserById(int id, String token) throws MyException,MyAuthException;
	public void deleteUserById(int id, String token)throws MyException, MyAuthException;
	
	public List<User> getAllUsers(String token) throws MyAuthException, MyException;
	public List<User> getAllUsersByAge(int age, String token) throws MyAuthException,MyException;
	public List<User> getAllUsersByName(String name, String token) throws MyAuthException,MyException;
}
