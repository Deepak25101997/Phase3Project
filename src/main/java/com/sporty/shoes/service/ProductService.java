package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.model.Product;

public interface ProductService {
	
	public List<Product> createProduct(List<Product> product);
	public Product updateProduct(Product product);
	public Product getProductById(int id);
	public void deleteProductById(int id);

	public List<Product> getAllProducts();
	public List<Product> getProductsByCategory(String category);
}
