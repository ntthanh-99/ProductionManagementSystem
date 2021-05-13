package com.tienthanh.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UseMaterial extends AbstractClass{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int quanlity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_line_id")
	private ProductionLine productionLine;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "export_material_bill")
	private ExportMaterialBill exportMaterialBill;
	
	public UseMaterial() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuanlity() {
		return quanlity;
	}

	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}

	public ProductionLine getProductionLine() {
		return productionLine;
	}

	public void setProductionLine(ProductionLine productionLine) {
		this.productionLine = productionLine;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public ExportMaterialBill getExportMaterialBill() {
		return exportMaterialBill;
	}

	public void setExportMaterialBill(ExportMaterialBill exportMaterialBill) {
		this.exportMaterialBill = exportMaterialBill;
	}
	
}
