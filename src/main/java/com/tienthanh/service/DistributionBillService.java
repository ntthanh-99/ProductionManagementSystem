package com.tienthanh.service;

import java.util.Date;
import java.util.List;

import com.tienthanh.domain.Distribution;
import com.tienthanh.domain.DistributionBill;
import com.tienthanh.domain.Employee;
import com.tienthanh.domain.ImportMaterial;
import com.tienthanh.domain.ImportMaterialBill;

public interface DistributionBillService {
	List<DistributionBill> findAll(Date date);

	DistributionBill findById(Long id);

	DistributionBill createBill(DistributionBill distributionBill, List<Distribution> distributionList,
			Employee employee);
}
