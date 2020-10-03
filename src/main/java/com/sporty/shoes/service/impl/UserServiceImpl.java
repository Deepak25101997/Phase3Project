package com.sporty.shoes.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.User;
import com.sporty.shoes.repository.UserRepository;
import com.sporty.shoes.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Override
	public List<User> createUser(List<User> user, String token) throws MyException {

		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return repo.saveAll(user);
	}

	@Override
	public User updateUser(User user) {
		return repo.save(user);
	}

	@Override
	public User getUserById(int id) throws MyException {

		User user = null;

		try {
			if (id <= 0) {
				throw new MyException("Id cannot be 0 or negative !");
			}
			user = repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new MyException("No user found with id " + id);
		}
		return user;
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
