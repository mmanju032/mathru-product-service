package com.manju.cloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manju.cloud.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

	List<Product> findByProductName(String pName);

}
