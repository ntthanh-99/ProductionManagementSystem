package com.tienthanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Distributor;
import com.tienthanh.repository.DistributorRepository;
import com.tienthanh.service.DistributorService;

@Service
public class DistributorServiceImpl implements DistributorService {
	@Autowired
	private DistributorRepository distributorRepository;

	@Override
	public List<Distributor> findAll() {
		// TODO Auto-generated method stub
		return (List<Distributor>) distributorRepository.findAll();
	}

	@Override
	public Distributor findById(Long id) {
		// TODO Auto-generated method stub
		return distributorRepository.findById(id).get();
	}

}
