package com.sporty.shoes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.model.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Integer>{
	
	@Query(value="Select password from admin_table where id=?", nativeQuery = true )
	public String findPasswordById(int id);
	
	
	@Query(value="update admin_table set password=? where id=?",nativeQuery = true)
	@Modifying
	@Transactional
	public void updatePassword(String newPass,int id);
	
	public Admin findByUsername(String name);
}
