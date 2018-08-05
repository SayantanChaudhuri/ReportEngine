package com.tiaa.reportengine.model.input.xsd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Cmfoodchain {

	private Branch branch;
	private Orders orders;

	@XmlElement 
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@XmlElement
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Cmfoodchain [branch=" + branch + ", orders=" + orders + "]";
	}
}
