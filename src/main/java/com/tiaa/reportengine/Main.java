package com.tiaa.reportengine;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws URISyntaxException {
		
		new ReportEngine(Paths.get(ClassLoader.getSystemResource("inputfiles").toURI()).toString(),
				Paths.get(ClassLoader.getSystemResource("outputfiles").toURI()).toString());

	}
}
