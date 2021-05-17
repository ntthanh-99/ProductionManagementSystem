package com.tienthanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Supplier;
import com.tienthanh.repository.SupplierRepository;
import com.tienthanh.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public List<Supplier> findAll() {
		// TODO Auto-generated method stub
		return (List<Supplier>) supplierRepository.findAll();
	}

}
