package com.sporty.shoes.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	public List<Product> findByCategory(String category);
	
	
	@Query(value="select id from product_table where category=?", nativeQuery = true )
	@Transactional
	public int[] findIdsOfCategory(String category);
	
}
