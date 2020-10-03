package com.sporty.shoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.model.Order;
import com.sporty.shoes.service.OrderService;

@RestController
public class OrderCRUDController {

	@Autowired
	private OrderService service;
	
	@PostMapping("/order/{uid}/product/{pid}")
	public Order createOrder(@PathVariable int uid, @PathVariable int pid,@RequestBody Order order) {
		return service.createOrder(uid,pid,order);
	}
	
	@GetMapping("/order/{id}")
	public Order getOrderById(@PathVariable int id) {
		return service.getOrderById(id);
	}
	
	@DeleteMapping("/order/{id}")
	public void deleteOrderById(@PathVariable int id) {
		service.deleteOrderById(id);
	}
	
}
