package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Company_Table")
@DynamicUpdate
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cId;
	@Column(name = "cName", length = 100, nullable = false)
	private String cName;
	@Column(name = "cAddress", length = 100, nullable = false)
	private String cAddress;

	public Company() {
		// TODO Auto-generated constructor stub
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public Company(int cId, String cName, String cAddress) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cAddress = cAddress;
	}

	@Override
	public String toString() {
		return "Company [cId=" + cId + ", cName=" + cName + ", cAddress=" + cAddress + "]";
	}

}
