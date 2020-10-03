package com.sporty.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.shoes.model.Product;
import com.sporty.shoes.service.ProductService;

@RestController
public class ProductCRUDController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/product")
	public List<Product> createProduct(@RequestBody List<Product> product){
		return service.createProduct(product);
	}
	
	@PutMapping("/product")
	public  Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}
	
	@DeleteMapping("/product/{id}")
	public void deleteProductBydId(@PathVariable int id) {
		service.deleteProductById(id);
	}
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable int id) {
		return service.getProductById(id);
	}
	
}
