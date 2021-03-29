package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Embeddable
@Entity
@Table(name = "Address_Table")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;

	@Column(name = "state", length = 40)
	private String countryName;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(int aid, String countryName, Employee employee) {
		super();
		this.aid = aid;
		this.countryName = countryName;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Address [aid=" + aid + ", countryName=" + countryName + ", employee=" + employee + "]";
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}