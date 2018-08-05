package com.tiaa.reportengine.model.input.json;


public class Branch {

	private String location;
	private String totalcollection;
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

	@Override
	public String toString() {
		return "Branch [location=" + location + ", totalcollection=" + totalcollection + ", locationid=" + locationid
				+ "]";
	}
}