package com.tienthanh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.ExportMaterialBill;
import com.tienthanh.domain.Material;
import com.tienthanh.domain.UseMaterial;
import com.tienthanh.repository.ExportMaterialBillRepository;
import com.tienthanh.repository.MaterialRepository;
import com.tienthanh.service.ExportBillService;
import com.tienthanh.service.MaterialService;

@Service
public class ExportMaterialServiceImpl implements ExportBillService {
	@Autowired
	private ExportMaterialBillRepository exportMaterialBillRepository;

	@Autowired
	private MaterialRepository materialRepository;

	@Override
	public List<ExportMaterialBill> findTenExportBill(Date date) {
		// TODO Auto-generated method stub
		return (List<ExportMaterialBill>) exportMaterialBillRepository.findAll();
	}

	@Override
	public ExportMaterialBill findById(Long id) {
		// TODO Auto-generated method stub
		return exportMaterialBillRepository.findById(id).get();
	}

	@Override
	public ExportMaterialBill createBill(ExportMaterialBill exportMaterialBill, List<UseMaterial> exportMaterialList,
			Employee employee) {
		// TODO Auto-generated method stub
		exportMaterialBill.setEmployee(employee);
		exportMaterialBill.setUseMaterialList(exportMaterialList);

		for (int i = 0; i < exportMaterialList.size(); i++) {
			exportMaterialList.get(i).setExportMaterialBill(exportMaterialBill);
			Material material = exportMaterialList.get(i).getMaterial();
			material.setQuanlity(material.getQuanlity() - exportMaterialList.get(i).getQuanlity());
			materialRepository.save(material);
		}
		exportMaterialBillRepository.save(exportMaterialBill);

		return exportMaterialBill;

	}

}
