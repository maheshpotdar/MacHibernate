package com.test.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid", length = 100, nullable = false)
	private int cid;

	@Column(name = "cname", length = 100, nullable = false)
	private String cname;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ElementCollection
	@JoinColumn(name = "address_id")
	private List<Address> addresses;

	public Country() {
		// TODO Auto-generated constructor stub
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Country(int cid, String cname, List<Address> addresses) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Country [cid=" + cid + ", cname=" + cname + ", addresses=" + addresses + "]";
	}

}
