package com.tiaa.reportengine.model.output.json;


public class Branch {

	private String location;
	private String totalcollection;
	private String sumoforder;
	private String locationid;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTotalcollection() {
		return totalcollection;
	}

	public void setTotalcollection(String totalcollection) {
		this.totalcollection = totalcollection;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public String getSumoforder() {
		return sumoforder;
	}

	public void setSumoforder(String sumoforder) {
		this.sumoforder = sumoforder;
	}

	@Override
	public String toString() {
		return "Branch [location=" + location + ", totalcollection=" + totalcollection + ", sumoforder=" + sumoforder
				+ ", locationid=" + locationid + "]";
	}
}