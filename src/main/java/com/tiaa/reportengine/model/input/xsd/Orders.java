package com.tiaa.reportengine.model.input.xsd;

import java.util.List;

public class Orders {

	private List<Orderdetail> orderdetail = null;

	public List<Orderdetail> getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(List<Orderdetail> orderdetail) {
		this.orderdetail = orderdetail;
	}
}