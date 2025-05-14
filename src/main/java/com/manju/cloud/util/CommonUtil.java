package com.manju.cloud.util;

import com.manju.cloud.entity.Product;

public class CommonUtil {

	public static boolean isValidRequest(Product prodRequest) {

		return prodRequest.getQuantity() < 10;
	}

	public double calulateFinalPrice(Product prodRequest) throws Exception {

		if (isValidRequest(prodRequest)) {
			return calculateDiscPrice(prodRequest);
		} else {
			throw new Exception("Not a valid quantity, please check the input");
		}
	}

	private double calculateDiscPrice(Product prodRequest) {
		
		//TODO: apply promo code discount logic here
		return prodRequest.getPrice() * prodRequest.getQuantity();
	}

}
