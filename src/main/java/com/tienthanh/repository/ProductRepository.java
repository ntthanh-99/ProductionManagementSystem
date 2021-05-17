package com.tienthanh.repository;

import org.springframework.data.repository.CrudRepository;

import com.tienthanh.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
}
