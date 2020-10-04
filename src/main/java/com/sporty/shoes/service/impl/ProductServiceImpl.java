package com.sporty.shoes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
	
	@Autowired
	private ProductService productService;

	@Override
	public List<Product> createProduct(List<Product> product, String token) throws MyAuthException, MyException {

		List<Product> products = new ArrayList<>();

		try {

			if (token == null)
				throw new MyAuthException("Token Missing !!");

			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				if (product.size() == 0)
					throw new MyException("Product Data missing. Please pass some data to be inserted !!");
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

			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {
				if (product.getName() == null || product.getCategory() == null || product.getPrice() <= 0.0d)
					throw new MyException("Product Data missing. Please pass appropriate data !!");
				
				@SuppressWarnings("unused")
				Product tempProduct2 = productService.getProductById(product.getId(), token);
				
				tempProduct = repo.save(product);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}catch (NoSuchElementException e) {
			throw new NoSuchElementException("No product found with id" + product.getId() + "Updation Failed");
		}

		return tempProduct;
	}

	@Override
	public Product getProductById(int id, String token) throws MyAuthException, MyException {

		Product product = null;

		try {
			boolean isValidToken = AuthService.validateToken(token);
			if (isValidToken) {

				if (id <= 0) {
					throw new MyException("Id cannot be 0 or negative !");
				}

				product = repo.findById(id).get();
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No product found with id" + id);
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

				@SuppressWarnings("unused")
				Product product = repo.findById(id).get();
				repo.deleteById(id);
			}
		} catch (MyAuthException e) {
			throw new MyAuthException(e.getMessage());
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}catch (NoSuchElementException e) {
			throw new NoSuchElementException("No product exists with id " + id);
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
		}catch (NoSuchElementException e) {
			throw new NoSuchElementException("No products found !!");
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
		}catch (NoSuchElementException e) {
			throw new NoSuchElementException("No product found for category " + category);
		}

		return products;
	}

}
