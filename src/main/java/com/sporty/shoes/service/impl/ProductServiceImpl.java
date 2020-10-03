package com.sporty.shoes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.model.Product;
import com.sporty.shoes.repository.ProductRepository;
import com.sporty.shoes.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repo; 
	
	@Override
	public List<Product> createProduct(List<Product> product) {
		return repo.saveAll(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public Product getProductById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteProductById(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return repo.findByCategory(category);
	}

}
