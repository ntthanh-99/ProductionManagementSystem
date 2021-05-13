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
public class ExportMaterialBill extends AbstractClass{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "exportMaterialBill")
	private List<UseMaterial> useMaterialList;

	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	public ExportMaterialBill() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UseMaterial> getUseMaterialList() {
		return useMaterialList;
	}

	public void setUseMaterialList(List<UseMaterial> useMaterialList) {
		this.useMaterialList = useMaterialList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
