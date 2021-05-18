package com.tienthanh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.ImportMaterial;
import com.tienthanh.domain.ImportMaterialBill;
import com.tienthanh.domain.Material;
import com.tienthanh.repository.ImportMaterialBillRepository;
import com.tienthanh.repository.MaterialRepository;
import com.tienthanh.service.ImportBillService;

@Service
public class ImportBillServiceImp implements ImportBillService {
	@Autowired
	private ImportMaterialBillRepository importMaterialBillRepository;

	@Autowired
	private MaterialRepository materialRepository;

	@Override
	public List<ImportMaterialBill> findTenImportBill(Date date) {
		// TODO Auto-generated method stub
		List<ImportMaterialBill> all = (List<ImportMaterialBill>) importMaterialBillRepository.findAll();

		return all;
	}

	@Override
	public ImportMaterialBill findById(Long id) {
		// TODO Auto-generated method stub
		return importMaterialBillRepository.findById(id).get();
	}

	@Override
	public ImportMaterialBill createBill(ImportMaterialBill importMaterialBill,
			List<ImportMaterial> importMaterialList, Employee employee) {
		// TODO Auto-generated method stub
		importMaterialBill.setEmployee(employee);
		importMaterialBill.setImportMaterialList(importMaterialList);
		
		for (int i = 0; i < importMaterialList.size(); i++) {
			importMaterialList.get(i).setImportMaterialBill(importMaterialBill);
			Material material = importMaterialList.get(i).getMaterial();
			material.setQuanlity(material.getQuanlity() + importMaterialList.get(i).getQuanlity());
			materialRepository.save(material);
		}
		importMaterialBillRepository.save(importMaterialBill);
		return importMaterialBill;
	}

}
