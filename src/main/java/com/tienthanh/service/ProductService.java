package com.tienthanh.service;

import java.util.List;

import com.tienthanh.domain.Product;

public interface ProductService {
	List<Product> findAll();
	
	Product save(Product product);
	
	Product findById(Long id);
	
	void deleleById(Long id);
}
