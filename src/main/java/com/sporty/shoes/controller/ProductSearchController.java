package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Product;
import com.sporty.shoes.service.ProductService;

@RestController
public class ProductSearchController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(@RequestHeader("token") String token) throws MyAuthException, MyException{
		return service.getAllProducts(token);
	}
	
	@GetMapping("/products/category/{category}")
	public List<Product> getProductsByCategory(@PathVariable String category, @RequestHeader("token") String token)
			throws MyAuthException, MyException{
		return service.getProductsByCategory(category,token);
	}
	
}
