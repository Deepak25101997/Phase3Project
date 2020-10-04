package com.sporty.shoes.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.exceptionHandler.MyAuthException;
import com.sporty.shoes.exceptionHandler.MyException;
import com.sporty.shoes.model.Product;
import com.sporty.shoes.repository.ProductRepository;
import com.sporty.shoes.service.AuthenticationService;
import com.sporty.shoes.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repo;

	@Autowired
	private AuthenticationService AuthService;

	@Override
	public List<Product> createProduct(List<Product> product, String token) throws MyAuthException, MyException {

		List<Product> products = new ArrayList<>();

		try {

			if (token == null)
				throw new MyAuthException("Token Missing !!");
			if (product == null)
				throw new MyException("Product Data missing. Please pass some data to be inserted !!");
			
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				products = repo.saveAll(product);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}

		return products;
	}

	@Override
	public Product updateProduct(Product product, String token) throws MyAuthException, MyException {
		
		Product tempProduct = null;
		
		try {
			if (token == null)
				throw new MyAuthException("Token Missing !!");
			if (product == null)
				throw new MyException("Product Data missing. Please pass some data to be inserted !!");
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				tempProduct = repo.save(product);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
		
		return  tempProduct;
	}

	@Override
	public Product getProductById(int id, String token) throws MyAuthException, MyException {
		
		Product product=null;
		
		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				product = repo.findById(id).get();
				if (product == null)
					throw new MyException("No product found with id" + id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
		
		return product;
	}

	@Override
	public void deleteProductById(int id, String token) throws MyAuthException, MyException {
		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				Product product = repo.findById(id).get();
				if (product == null)
					throw new MyException("No product exists with id " + id);
				repo.deleteById(id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
	}

	@Override
	public List<Product> getAllProducts(String token) throws MyAuthException, MyException {
		
		List<Product> products = new ArrayList<>();
		
		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				products = repo.findAll();
				if (products.size() <= 0)
					throw new MyException("No products found !!!");
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
		
		return products;
	}

	@Override
	public List<Product> getProductsByCategory(String category, String token) throws MyAuthException, MyException {
		
		List<Product> products = new ArrayList<>();
		
		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (category == null) {
					throw new MyException("Catgory cant be null !!");
				}
				products = repo.findByCategory(category);
				if (products.size() <= 0)
					throw new MyException("No product found for category " + category);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
		
		return products; 
	}

}
