package com.tienthanh.service;

import java.util.Date;
import java.util.List;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.ImportMaterial;
import com.tienthanh.domain.ImportMaterialBill;

public interface ImportBillService {
	List<ImportMaterialBill> findTenImportBill(Date date);

	ImportMaterialBill findById(Long id);

	ImportMaterialBill createBill(ImportMaterialBill importMaterialBill, List<ImportMaterial> importMaterialList,
			Employee employee);
}
