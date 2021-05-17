package com.tienthanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.ProductionLine;
import com.tienthanh.repository.ProductRepository;
import com.tienthanh.repository.ProductionLineRepository;
import com.tienthanh.service.ProductionLineService;

@Service
public class ProductionLineServiceImpl implements ProductionLineService{
	@Autowired
	private ProductionLineRepository productionLineRepository;
	
	@Override
	public List<ProductionLine> findAll() {
		// TODO Auto-generated method stub
		return (List<ProductionLine>) productionLineRepository.findAll();
	}

	@Override
	public ProductionLine save(ProductionLine productionLine) {
		// TODO Auto-generated method stub
		return productionLineRepository.save(productionLine);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		productionLineRepository.deleteById(id);
		return;
	}

	@Override
	public ProductionLine findById(Long id) {
		// TODO Auto-generated method stub
		return productionLineRepository.findById(id).get();
	}
	
}
