package com.sporty.shoes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.model.Order;
import com.sporty.shoes.model.Product;
import com.sporty.shoes.model.User;
import com.sporty.shoes.repository.OrderRepository;
import com.sporty.shoes.service.OrderService;
import com.sporty.shoes.service.ProductService;
import com.sporty.shoes.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repo;

	@Autowired
	private UserService uService;

	@Autowired
	private ProductService pService;

	@Override
	public Order createOrder(int uid, int pid, Order order) {

		// getting the user and product by their ids
		User user = uService.getUserById(uid);
		Product product = pService.getProductById(pid);

		// saving them in the order entity
		order.setUser(user);
		order.setProduct(product);

		return repo.save(order);
	}

	@Override
	public Order getOrderById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteOrderById(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Order> getAllOrders() {
		return repo.findAll();
	}

	@Override
	public List<Order> getAllOrdersByDate(String date) {
		return repo.findByDate(date);
	}

	@Override
	public List<Order> getAllOrdersByCategory(String category) {
		return repo.getAllOrdersByCategory(category);
	}

	@Override
	public List<Order> getAllOrdersByDateAndCategory(String date, String category) {
		return repo.findByDateAndCategory(date, category);
	}

}
