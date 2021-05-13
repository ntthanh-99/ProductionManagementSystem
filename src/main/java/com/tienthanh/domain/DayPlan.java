package com.tienthanh.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DayPlan extends AbstractClass{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String numberInWeek; // thứ tự trong tuần -> vd: thứ HAI
	private Date date;
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_line_id")
	private ProductionLine productionLine;
	
	@ManyToOne
	@JoinColumn(name = "week_plan_id")
	private WeekPlan weekPlan;
	
	public DayPlan() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumberInWeek() {
		return numberInWeek;
	}

	public void setNumberInWeek(String numberInWeek) {
		this.numberInWeek = numberInWeek;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProductionLine getProductionLine() {
		return productionLine;
	}

	public void setProductionLine(ProductionLine productionLine) {
		this.productionLine = productionLine;
	}

	public WeekPlan getWeekPlan() {
		return weekPlan;
	}

	public void setWeekPlan(WeekPlan weekPlan) {
		this.weekPlan = weekPlan;
	}
	
}
