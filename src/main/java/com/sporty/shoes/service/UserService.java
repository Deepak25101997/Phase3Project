package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.User;

public interface UserService {
	
	public List<User> createUser(List<User> user, String token) throws MyException;
	public User updateUser(User user);
	public User getUserById(int id) throws MyException;
	public void deleteUserById(int id);
	
	public List<User> getAllUsers();
	public List<User> getAllUsersByAge(int age);
	public List<User> getAllUsersByName(String name);
}
