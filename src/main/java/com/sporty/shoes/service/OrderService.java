package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Order;

public interface OrderService {
	
	public Order createOrder(int uid,int pid,Order order) throws MyException;
	public Order getOrderById(int id);
	public void deleteOrderById(int id);
	
	
	public List<Order>getAllOrders();
	public List<Order>getAllOrdersByDate(String date);
	public List<Order>getAllOrdersByCategory(String category);
	public List<Order>getAllOrdersByDateAndCategory(String date, String category);

}
