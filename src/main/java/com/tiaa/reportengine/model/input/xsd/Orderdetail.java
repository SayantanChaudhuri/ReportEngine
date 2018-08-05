package com.tiaa.reportengine.model.input.xsd;

import javax.xml.bind.annotation.XmlElement;

public class Orderdetail {

	private Integer orderid;
	private String billamount;

	@XmlElement
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@XmlElement
	public String getBillamount() {
		return billamount;
	}

	public void setBillamount(String billamount) {
		this.billamount = billamount;
	}
}