package com.tiaa.reportengine.model.input.json;

import java.util.List;

public class Orders {

	private List<Orderdetail> orderdetail = null;

	public List<Orderdetail> getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(List<Orderdetail> orderdetail) {
		this.orderdetail = orderdetail;
	}

	@Override
	public String toString() {
		return "Orders [orderdetail=" + orderdetail + "]";
	}
}