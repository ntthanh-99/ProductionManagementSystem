package com.tienthanh.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.web.PageableDefault;

@Entity
public class LinePlan extends AbstractClass {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String status;
	private Date date;

	@ManyToOne
	@JoinColumn(name = "production_line_id")
	private ProductionLine productionLine;

	@ManyToOne
	@JoinColumn(name = "day_plan_id")
	private DayPlan dayPlan;

	public LinePlan() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ProductionLine getProductionLine() {
		return productionLine;
	}

	public void setProductionLine(ProductionLine productionLine) {
		this.productionLine = productionLine;
	}

	public DayPlan getDayPlan() {
		return dayPlan;
	}

	public void setDayPlan(DayPlan dayPlan) {
		this.dayPlan = dayPlan;
	}
}
