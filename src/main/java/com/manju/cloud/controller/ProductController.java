package com.manju.cloud.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manju.cloud.entity.Product;
import com.manju.cloud.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping(value="/addProduct",produces=MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public Product addProduct(@RequestBody Product product) {
		return service.saveProduct(product);
		
	}
	
	@PutMapping(value="/updateProduct",produces=MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public Product updateProduct(@RequestBody Product product) {
		return service.saveProduct(product);
		
	}
	
	@GetMapping(value="/findProductByName/{pName}")
	public List<Product> findProductByName(@PathVariable String pName) {
		return service.searchProductByName(pName);
		
	}
	
	@DeleteMapping(value="/deletProduct/{productId}")
	public String deleteProductById(@PathVariable int productId) {
		return service.deleteProduct(productId);
		
	}
	
	

}
