package com.tiaa.reportengine.exception;

public class ReportException extends RuntimeException {

	private static final long serialVersionUID = 6642528119962951515L;

	public ReportException(Exception e) {
		super(e);
	}
	
	public ReportException(String message) {
		super(new Exception(message));
	}
}
