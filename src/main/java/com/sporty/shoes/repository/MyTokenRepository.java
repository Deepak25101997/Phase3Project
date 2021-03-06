package com.sporty.shoes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.model.MyToken;

@Repository
public interface MyTokenRepository extends JpaRepository<MyToken, Integer>{

	public MyToken findByTokenValue(String tokenValue);
	
	@Transactional
	public void deleteByTokenValue(String tokenValue);
}
