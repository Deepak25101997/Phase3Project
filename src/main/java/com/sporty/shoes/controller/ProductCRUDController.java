package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Product;
import com.sporty.shoes.service.ProductService;

@RestController
public class ProductCRUDController {

	@Autowired
	private ProductService service;

	@PostMapping("/product")
	public List<Product> createProduct(@RequestBody List<Product> product, @RequestHeader("token") String token)
			throws MyAuthException, MyException {
		return service.createProduct(product, token);
	}

	@PutMapping("/product")
	public Product updateProduct(@RequestBody Product product, @RequestHeader("token") String token)
			throws MyAuthException, MyException {
		return service.updateProduct(product, token);
	}

	@DeleteMapping("/product/{id}")
	public void deleteProductBydId(@PathVariable int id, @RequestHeader("token") String token)
			throws MyAuthException, MyException {
		service.deleteProductById(id, token);
	}

	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable int id, @RequestHeader("token") String token)
			throws MyAuthException, MyException {
		return service.getProductById(id, token);
	}

}
