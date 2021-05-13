package com.tienthanh.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ImportMaterialBill extends AbstractClass{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double total;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "importMaterialBill")
	private List<ImportMaterial> importMaterialList;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<ImportMaterial> getImportMaterialList() {
		return importMaterialList;
	}

	public void setImportMaterialList(List<ImportMaterial> importMaterialList) {
		this.importMaterialList = importMaterialList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
