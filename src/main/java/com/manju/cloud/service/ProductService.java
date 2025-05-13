package com.manju.cloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manju.cloud.entity.Product;
import com.manju.cloud.repository.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	@HystrixCommand(fallbackMethod="saveProductFallback",
	commandProperties= { 
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="2000")})
	public Product saveProduct(Product product) {
		return repo.save(product);
	}

	@HystrixCommand(fallbackMethod="searchProductByNameFallback",
	commandProperties= { 
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="2000")})
	public List<Product> searchProductByName(String pName) {
		return repo.findByProductName(pName);
	}

	public String deleteProduct(int productId) {
		repo.deleteById(productId);
		return "success";
	}
	
	public Product saveProductFallback(Product product) {
		return null;
	}
	
	public List<Product> searchProductByNameFallback(String pName) {		
		return new ArrayList<>();
	}

}
