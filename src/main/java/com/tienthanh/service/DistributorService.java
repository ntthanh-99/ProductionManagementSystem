package com.tienthanh.service;

import java.util.List;

import com.tienthanh.domain.Distributor;

public interface DistributorService {
	List<Distributor> findAll();

	Distributor findById(Long id);
}
