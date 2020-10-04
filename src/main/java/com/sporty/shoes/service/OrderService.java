package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Order;

public interface OrderService {

	public Order createOrder(int uid, int pid, Order order, String token) throws MyException, MyAuthException;

	public Order getOrderById(int id, String token) throws MyException, MyAuthException;

	public void deleteOrderById(int id, String token) throws MyException, MyAuthException;

	public List<Order> getAllOrders(String token) throws MyException, MyAuthException;

	public List<Order> getAllOrdersByDate(String date, String token) throws MyException, MyAuthException;

	public List<Order> getAllOrdersByCategory(String category, String token) throws MyException, MyAuthException;

	public List<Order> getAllOrdersByDateAndCategory(String date, String category, String token)
			throws MyException, MyAuthException;

}
