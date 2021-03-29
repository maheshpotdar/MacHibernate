package com.test.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {

	@Id
	@Column(name = "aid", length = 100, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	@Column(name = "aname", length = 100, nullable = false)
	private String aname;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	 

	@ManyToOne(targetEntity = Country.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//fetch = FetchType.LAZY means address kade country magitlas taracha denar nahi tar nahi.
	@ElementCollection
	private Country country;

	public Address(int aid, String aname, Employee employee,Country country) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.employee = employee;
this.country = country;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	@Override
	public String toString() {
		return "Address [aid=" + aid + ", aname=" + aname + ", employee=" + employee + ", country=" + country + "]";
	}

}
