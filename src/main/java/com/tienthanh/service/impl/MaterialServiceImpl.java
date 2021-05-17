package com.tienthanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Material;
import com.tienthanh.repository.MaterialRepository;
import com.tienthanh.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	private MaterialRepository materialRepository;

	@Override
	public List<Material> findAll() {
		// TODO Auto-generated method stub
		return (List<Material>) materialRepository.findAll();
	}

	@Override
	public Material findById(Long id) {
		// TODO Auto-generated method stub
		return materialRepository.findById(id).get();
	}

	@Override
	public Material save(Material material) {
		// TODO Auto-generated method stub
		return materialRepository.save(material);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		materialRepository.deleteById(id);
		return;
	}

}
