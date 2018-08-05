package com.tiaa.reportengine.model.input.json;

public class Cmfoodchain {

	private Branch branch;
	private Orders orders;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

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