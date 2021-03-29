package com.test.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Employee")

// fakt address karayacha ahe tar id user ne dileli id ani db madhali id equal ahe kay
// name  user ne dileli object madhil name ani db madhali name  equal ahe kay.
// mag  user ne dileli object madhil address ani db madhali address  diffrent tar db madhil address update.
//eka address column sathi bakihe check karayachi garaj ahe noooo. so use dirty checking @DynamicUpdate use kara....
@DynamicUpdate
public class Employee implements Serializable {
	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identity means seprate Table tayara karat nahi for id. other
														// similar to AUTO.
	private int id;

	@Column(name = "fname", nullable = false, length = 50)
	private String fname;

	@Column(name = "lname", nullable = false, length = 40)
	private String lname;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	@ElementCollection
	private List<Address> address;

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
  

	public Employee(int id, String fname, String lname, List<Address> address) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", address=" + address + "]";
	}


}
