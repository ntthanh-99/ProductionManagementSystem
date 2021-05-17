package com.tienthanh.service;

import java.util.List;

import com.tienthanh.domain.ProductionLine;

public interface ProductionLineService {
	List<ProductionLine> findAll();
	
	ProductionLine findById(Long id);
	
	ProductionLine save(ProductionLine productionLine);
	
	void deleteById(Long id);
}
