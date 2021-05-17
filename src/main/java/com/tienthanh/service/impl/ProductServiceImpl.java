package com.tienthanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Product;
import com.tienthanh.repository.ProductRepository;
import com.tienthanh.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Product findById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).get();
	}

	@Override
	public void deleleById(Long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
		return;
	}

}
