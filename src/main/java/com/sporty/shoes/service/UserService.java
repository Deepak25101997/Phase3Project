package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.model.User;

public interface UserService {
	
	public List<User> createUser(List<User> user);
	public User updateUser(User user);
	public User getUserById(int id);
	public void deleteUserById(int id);
	
	public List<User> getAllUsers();
	public List<User> getAllUsersByAge(int age);
	public List<User> getAllUsersByName(String name);
}
