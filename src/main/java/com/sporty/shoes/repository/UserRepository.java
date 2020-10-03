package com.sporty.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByAge(int age);
	public List<User> findByNameContainingIgnoreCase(String name);
}
