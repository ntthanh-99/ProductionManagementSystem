package com.tienthanh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Distribution;
import com.tienthanh.domain.DistributionBill;
import com.tienthanh.domain.Employee;
import com.tienthanh.domain.Material;
import com.tienthanh.domain.Product;
import com.tienthanh.repository.DistributionBillRepository;
import com.tienthanh.repository.ProductRepository;
import com.tienthanh.service.DistributionBillService;
import com.tienthanh.service.DistributorService;

@Service
public class DistributionBillServiceImpl implements DistributionBillService {
	@Autowired
	private DistributionBillRepository distributionBillRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<DistributionBill> findAll(Date date) {
		// TODO Auto-generated method stub
		return (List<DistributionBill>) distributionBillRepository.findAll();
	}

	@Override
	public DistributionBill findById(Long id) {
		// TODO Auto-generated method stub
		return distributionBillRepository.findById(id).get();
	}

	@Override
	public DistributionBill createBill(DistributionBill distributionBill, List<Distribution> distributionList,
			Employee employee) {
		// TODO Auto-generated method stub
		distributionBill.setEmployee(employee);
		distributionBill.setDistributionList(distributionList);

		for (int i = 0; i < distributionList.size(); i++) {
			distributionList.get(i).setDistributionBill(distributionBill);
			Product product = distributionList.get(i).getProduct();
			product.setQuanlity(product.getQuanlity() - distributionList.get(i).getQuanlity());
			productRepository.save(product);
		}
		distributionBillRepository.save(distributionBill);
		return distributionBill;
	}

}
