package com.tiaa.reportengine;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.tiaa.reportengine.exception.ReportException;

public class Main {

	private static ReportEngine reportEngine = new ReportEngine(getInputFileLocation("inputfiles"),
			getOutputFileLocation("outputfiles"));

	public static void main(String[] args) {

		setReportEngine(reportEngine);
	}

	public static void setReportEngine(ReportEngine reportEngine) {
		Main.reportEngine = reportEngine;
	}

	static String getInputFileLocation(String inputDirName) {

		String inputFileLocation = null;

		try {
			inputFileLocation = Paths.get(ClassLoader.getSystemResource(inputDirName).toURI()).toString();
		} catch (Exception e) {
			throw new ReportException(e.getMessage());
		}

		return inputFileLocation;
	}

	static String getOutputFileLocation(String outputDirName) {

		String outputFileLocation = null;

		try {
			outputFileLocation = Paths.get(ClassLoader.getSystemResource(outputDirName).toURI()).toString();
		} catch (Exception e) {
			throw new ReportException(e.getMessage());
		}

		return outputFileLocation;
	}
}
