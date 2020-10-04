package com.sporty.shoes.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.User;
import com.sporty.shoes.repository.UserRepository;
import com.sporty.shoes.service.AuthenticationService;
import com.sporty.shoes.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private AuthenticationService AuthService;

	@Override
	public List<User> createUser(List<User> user, String token) throws MyException, MyAuthException {

		List<User> users = new ArrayList<>();

		try {
			if (token == null)
				throw new MyAuthException("Token Missing !!");
			if (user == null)
				throw new MyException("User Data missing. Please pass some data to be inserted !!");
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				users = repo.saveAll(user);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return users;

	}

	@Override
	public User updateUser(User user, String token) throws MyAuthException, MyException {

		User tempUser = null;

		try {
			if (token == null)
				throw new MyAuthException("Token Missing !!");
			if (user == null)
				throw new MyException("User Data missing. Please pass some data to be inserted !!");
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				tempUser = repo.save(user);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return tempUser;
	}

	@Override
	public User getUserById(int id, String token) throws MyException, MyAuthException {

		User user = null;

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				user = repo.findById(id).get();
				if (user == null)
					throw new MyException("No user found with id" + id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return user;
	}

	@Override
	public void deleteUserById(int id, String token) throws MyException, MyAuthException {

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				User user = repo.findById(id).get();
				if (user == null)
					throw new MyException("No user exists with id " + id);
				repo.deleteById(id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
	}

	@Override
	public List<User> getAllUsers(String token) throws MyAuthException, MyException {

		List<User> users = new ArrayList<>();

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				users = repo.findAll();
				if (users.size() <= 0)
					throw new MyException("No users found !!!");
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return users;
	}

	@Override
	public List<User> getAllUsersByAge(int age, String token) throws MyAuthException, MyException {

		List<User> users = new ArrayList<>();

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (age <= 0) {
					throw new MyException("Age cannot be 0 or negative !");
				}
				users = repo.findByAge(age);
				if (users.size() <= 0)
					throw new MyException("No user found for age " + age);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return users;
	}

	@Override
	public List<User> getAllUsersByName(String name, String token) throws MyException, MyAuthException {

		List<User> users = new ArrayList<>();

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				if (name == null) {
					throw new MyException("Name cannot be null !");
				}
				users = repo.findByNameContainingIgnoreCase(name);
				if (users.size() <= 0)
					throw new MyException("No users found with name " + name);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return users;
	}

}
