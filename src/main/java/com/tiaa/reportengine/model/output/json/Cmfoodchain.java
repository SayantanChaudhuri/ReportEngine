package com.tiaa.reportengine.model.output.json;

import java.util.List;

public class Cmfoodchain {

	private List<Branch> branch;

	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}


	@Override
	public String toString() {
		return "Cmfoodchain [branch=" + branch + "]";
	}

}