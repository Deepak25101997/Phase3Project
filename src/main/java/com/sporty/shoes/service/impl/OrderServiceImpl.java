package com.sporty.shoes.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Order;
import com.sporty.shoes.model.Product;
import com.sporty.shoes.model.User;
import com.sporty.shoes.repository.OrderRepository;
import com.sporty.shoes.service.AuthenticationService;
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

	@Autowired
	private AuthenticationService AuthService;

	@Override
	public Order createOrder(int uid, int pid, Order order, String token) throws MyException, MyAuthException {

		Order tempOrder = new Order();

		try {

			if (token == null)
				throw new MyAuthException("Token Missing !!");
			if (uid <= 0)
				throw new MyException("Id cannot be 0 or negative !!");
			if (pid <= 0)
				throw new MyException("Id cannot be 0 or negative !!");
			if (order == null)
				throw new MyException("Order cannot be null. Pass some data !");

			boolean isValidToken = AuthService.validateToken(token);

			if (isValidToken) {
				// getting the user and product by their ids
				User user = uService.getUserById(uid, token);
				if (user == null)
					throw new MyException("No user found with id " + uid);
				Product product = pService.getProductById(pid, token);
				if (product == null)
					throw new MyException("No product found with id " + pid);
				// saving them in the order entity
				tempOrder.setUser(user);
				tempOrder.setProduct(product);

			}

		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return tempOrder;
	}

	@Override
	public Order getOrderById(int id, String token) throws MyException, MyAuthException {

		Order order = null;

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				order = repo.findById(id).get();
				if (order == null)
					throw new MyException("No order found with id" + id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return order;
	}

	@Override
	public void deleteOrderById(int id, String token) throws MyException, MyAuthException {

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				Order order = repo.findById(id).get();
				if (order == null)
					throw new MyException("No order exists with id " + id);
				repo.deleteById(id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

	}

	@Override
	public List<Order> getAllOrders(String token) throws MyException, MyAuthException {

		List<Order> orders = new ArrayList<>();

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				orders = repo.findAll();
				if (orders.size() <= 0)
					throw new MyException("No orders found !!!");
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
		return orders;
	}

	@Override
	public List<Order> getAllOrdersByDate(String date, String token) throws MyException, MyAuthException {

		List<Order> orders = new ArrayList<>();

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (date == null) {
					throw new MyException("Date cannot be null !");
				}
				orders = repo.findByDate(date);
				if (orders.size() <= 0)
					throw new MyException("No order found for date " + date);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return orders;
	}

	@Override
	public List<Order> getAllOrdersByCategory(String category, String token) throws MyException, MyAuthException {
		List<Order> orders = new ArrayList<>();

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (category == null) {
					throw new MyException("Category cannot be null !");
				}
				orders = repo.findByCategory(category);
				if (orders.size() <= 0)
					throw new MyException("No order found for category " + category);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return orders;
	}

	@Override
	public List<Order> getAllOrdersByDateAndCategory(String date, String category, String token)
			throws MyException, MyAuthException {
		List<Order> orders = new ArrayList<>();

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (category == null) {
					throw new MyException("Category cannot be null !");
				}
				if (date == null) {
					throw new MyException("Date cannot be null !");
				}
				orders = repo.findByDateAndCategory(date, category);
				if (orders.size() <= 0)
					throw new MyException("No order found for given date and category");
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return orders;
	}

}
