package com.sporty.shoes.service;

import java.util.List;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Product;

public interface ProductService {

	public List<Product> createProduct(List<Product> product, String token) throws MyAuthException, MyException;

	public Product updateProduct(Product product, String token) throws MyAuthException, MyException;

	public Product getProductById(int id, String token) throws MyAuthException, MyException;

	public void deleteProductById(int id, String token) throws MyAuthException, MyException;

	public List<Product> getAllProducts(String token) throws MyAuthException, MyException;

	public List<Product> getProductsByCategory(String category, String token) throws MyAuthException, MyException;
}
