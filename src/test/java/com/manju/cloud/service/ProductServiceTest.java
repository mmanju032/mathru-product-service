package com.manju.cloud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.manju.cloud.entity.Product;
import com.manju.cloud.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepository repo;
	
	private Product productEntity;
	

	@DisplayName("justCheckingBeforeAll")
	@BeforeAll
	public static void beforeAllTest() {
		
		System.out.println("this is a dummy test for BeforeAll method");
		// This method invoked only once at the beginning before other tests starts
		// make sure this is static method otherwise it will fail
		// else we need to use @TestInstance(LifeCycle.PER_CLASS) To use it at instance level 
				
	}
	
	@BeforeEach
	public void setup(TestInfo testInfo) {
		
		System.out.println("Before each invoked for : " +  testInfo.getDisplayName()) ;		
		productEntity = new Product();
		productEntity.setProductId(100);
		productEntity.setProductName("iPhone 16 pro");
		productEntity.setPrice(89000);
		productEntity.setQuantity(5);
		
	}
	
	@AfterEach
	public void cleanup(TestInfo testInfo) {
		
		System.out.println("cleanup invoked for : " +  testInfo.getDisplayName()) ;		
		
	}
	
	@Test
	@DisplayName("addProductForSuccess")
	public void saveProductTest() {
			
		when(repo.save(Mockito.any())).thenReturn(productEntity);
		
		Product saveProductResult = productService.saveProduct(productEntity);
		assertNotNull(saveProductResult);
		assertEquals(5,saveProductResult.getQuantity());
				
	}
	
	@Test
	@DisplayName("addProductForExceptions")
	public void saveProductTestException() {
			
		when(repo.save(Mockito.any())).thenThrow(new RuntimeException());
		
		Exception ex = assertThrows(RuntimeException.class, () -> productService.saveProduct(productEntity));
		assertEquals(RuntimeException.class, ex.getClass());
				
	}
	
	@Test
	@DisplayName("ignoreTestForDemo")
	@Disabled
	public void ingoreTest() {
		
		System.out.println("this is a dummy test for demo");		
				
	}
	
	@DisplayName("justCheckingAfterAll")
	@AfterAll
	public static void afterAllTest() {
		
		System.out.println("this is a dummy test for AfterAll method");
		// This method invoked only once at the end after all other tests are executed
		// make sure this is static method otherwise it will fail
		// else we need to use @TestInstance(LifeCycle.PER_CLASS) To use it at instance level 
				
	}

}
