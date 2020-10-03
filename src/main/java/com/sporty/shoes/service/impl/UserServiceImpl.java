package com.sporty.shoes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.model.User;
import com.sporty.shoes.repository.UserRepository;
import com.sporty.shoes.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<User> createUser(List<User> user) {
		return repo.saveAll(user);
	}

	@Override
	public User updateUser(User user) {
		return repo.save(user);
	}

	@Override
	public User getUserById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteUserById(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public List<User> getAllUsersByAge(int age) {
		return repo.findByAge(age);
	}

	@Override
	public List<User> getAllUsersByName(String name) {
		return repo.findByNameContainingIgnoreCase(name);
	}

}
