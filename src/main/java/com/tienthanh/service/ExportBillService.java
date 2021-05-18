package com.tienthanh.service;

import java.util.Date;
import java.util.List;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.ExportMaterialBill;
import com.tienthanh.domain.UseMaterial;

public interface ExportBillService {
	List<ExportMaterialBill> findTenExportBill(Date date);

	ExportMaterialBill findById(Long id);

	ExportMaterialBill createBill(ExportMaterialBill exportMaterialBill, List<UseMaterial> exportMaterialList,
			Employee employee);
}
