package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Order;
import com.sporty.shoes.service.OrderService;

@RestController
public class OrderSearchController {

	@Autowired
	private OrderService service;

	@GetMapping("/orders")
	public List<Order> getAllOrders(@RequestHeader("token") String token) throws MyException, MyAuthException {
		return service.getAllOrders(token);
	}

	@GetMapping("/orders/date/{date}")
	public List<Order> getAllOrdersByDate(@PathVariable String date, @RequestHeader("token") String token)
			throws MyException, MyAuthException {
		return service.getAllOrdersByDate(date, token);
	}

	@GetMapping("/orders/category/{category}")
	public List<Order> getAllOrdersByCategory(@PathVariable String category, @RequestHeader("token") String token)
			throws MyException, MyAuthException {
		return service.getAllOrdersByCategory(category, token);
	}

	@GetMapping("/orders/date/{date}/category/{category}")
	public List<Order> getAllOrdersByDateAndCategory(@PathVariable String date, @PathVariable String category,
			@RequestHeader("token") String token) throws MyException, MyAuthException {
		return service.getAllOrdersByDateAndCategory(date, category, token);
	}

	@GetMapping("/orders/user/{id}")
	public List<Order> getOrderByUserId(@PathVariable int id, @RequestHeader("token") String token) throws MyAuthException, MyException {
		return service.getOrdersByUserId(id, token);
	}

}
