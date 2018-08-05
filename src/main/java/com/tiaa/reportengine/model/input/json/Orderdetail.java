package com.tiaa.reportengine.model.input.json;

public class Orderdetail {

	private Integer orderid;
	private String billamount;

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getBillamount() {
		return billamount;
	}

	public void setBillamount(String billamount) {
		this.billamount = billamount;
	}

	@Override
	public String toString() {
		return "Orderdetail [orderid=" + orderid + ", billamount=" + billamount + "]";
	}

}