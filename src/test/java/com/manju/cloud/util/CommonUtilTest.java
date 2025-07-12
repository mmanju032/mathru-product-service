package com.manju.cloud.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.manju.cloud.entity.Product;


public class CommonUtilTest {

	@Test
	public void testPrivateMethodUsingReflection() {
		CommonUtil util = new CommonUtil();
		try {
			Method declaredMethod = util.getClass().getDeclaredMethod("calculateDiscPrice", Product.class);
			declaredMethod.setAccessible(true);

			// validateTest:
			Product prodRequest = new Product();
			prodRequest.setProductId(100);
			prodRequest.setProductName("iPhone 11");
			prodRequest.setPrice(10000);
			prodRequest.setQuantity(5);

			assertEquals(50000.00, declaredMethod.invoke(util, prodRequest));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testPrivateMethodUsingRefecltionUtil() {
		CommonUtil util = new CommonUtil();
		try {
			Optional<Method> findMethod = ReflectionUtils.findMethod(CommonUtil.class, "calculateDiscPrice",
					Product.class);
			if (findMethod.isPresent()) {
				Method declaredMethod = findMethod.get();
				declaredMethod.setAccessible(true);
				
				// validateTest:
				Product prodRequest = new Product();
				prodRequest.setProductId(100);
				prodRequest.setProductName("iPhone 11");
				prodRequest.setPrice(10000);
				prodRequest.setQuantity(5);

				assertEquals(50000.00, declaredMethod.invoke(util, prodRequest));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	@Test
	public void testStaticMethod() {
		
		try(MockedStatic<CommonUtil> mockStatic = Mockito.mockStatic(CommonUtil.class)){
			
			Product prodRequest = new Product();
			prodRequest.setProductId(100);
			prodRequest.setProductName("iPhone 11");
			prodRequest.setPrice(10000);
			prodRequest.setQuantity(5);
			
			mockStatic.when(() -> CommonUtil.isValidRequest(prodRequest)).thenReturn(true);
			
			assertEquals(true, CommonUtil.isValidRequest(prodRequest));
			
		}		
		
	}
	
	@Test
	public void testStaticMethod2() {
		try {			
			// validateTest:
			Product prodRequest = new Product();
			prodRequest.setProductId(100);
			prodRequest.setProductName("iPhone 11");
			prodRequest.setPrice(10000);
			prodRequest.setQuantity(5);

			assertEquals(true, CommonUtil.isValidRequest(prodRequest));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
