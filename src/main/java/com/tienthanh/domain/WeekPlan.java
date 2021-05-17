package com.tienthanh.domain;

import java.util.Date;
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
public class WeekPlan extends AbstractClass{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date startTime;
	private Date endTime;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "weekPlan")
	private List<LineWeekPlan> weekPlan;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	public WeekPlan() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<LineWeekPlan> getWeekPlan() {
		return weekPlan;
	}

	public void setWeekPlan(List<LineWeekPlan> weekPlan) {
		this.weekPlan = weekPlan;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
