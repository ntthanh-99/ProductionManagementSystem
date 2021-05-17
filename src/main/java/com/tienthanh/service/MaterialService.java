package com.tienthanh.service;

import java.util.List;

import com.tienthanh.domain.Material;

public interface MaterialService {
	List<Material> findAll();
	
	Material findById(Long id);

	Material save(Material material);

	void deleteById(Long id);

}
