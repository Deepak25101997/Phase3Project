package com.sporty.shoes.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	public List<Order> findByDate(String date);

	@Query(value = "Select * from order_table where product_id in :ids", nativeQuery = true)
	@Transactional
	public List<Order> findByCategory(@Param("ids") int[] ids);

	@Query(value = "Select * from order_table where date = :dd and product_id in :ids", nativeQuery = true)
	@Transactional
	public List<Order> findByDateAndCategory(@Param("dd") String date, @Param("ids") int[] ids);
	
	
	@Query(value = "Select * from order_table where user_id = :uid", nativeQuery = true)
	@Transactional
	public List<Order> findByUserId(@Param("uid") int uid);
	
	
}
