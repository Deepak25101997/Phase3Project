package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.model.Order;
import com.sporty.shoes.service.OrderService;

@RestController
public class OrderSearchController {

	@Autowired
	private OrderService service;

	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return service.getAllOrders();
	}
	
	@GetMapping("/orders/date/{date}")
	public List<Order> getAllOrdersByDate(@PathVariable String date){
		return service.getAllOrdersByDate(date);
	}

	@GetMapping("/orders/category/{category}")
	public List<Order> getAllOrdersByCategory(@PathVariable String category){
		return service.getAllOrdersByCategory(category);
	}
	
	@GetMapping("/orders/date/{date}/category/{category}")
	public List<Order> getAllOrdersByDateAndCategory(@PathVariable String date,@PathVariable String category){
		return service.getAllOrdersByDateAndCategory(date, category);
	}
	
}
