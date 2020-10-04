package com.sporty.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	public List<Order>findByDate(String date);
	
	@Query(value="Select * from order_table where product_id=(select id from product_table where category=?)", nativeQuery = true )
	public List<Order>findByCategory(String category);
	
	@Query(value="Select * from order_table where product_id=(select id from product_table where date=?1 and category=?2)", nativeQuery = true )
	public List<Order>findByDateAndCategory(String date, String category);
}
