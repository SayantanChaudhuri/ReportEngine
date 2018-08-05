package com.tiaa.reportengine;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.*;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiaa.reportengine.exception.ReportException;
import com.tiaa.reportengine.model.input.json.Cmfoodchain;
import com.tiaa.reportengine.model.output.json.Branch;

@RunWith(PowerMockRunner.class)
public class ReportEngineTestCases {

	@Mock
	Set<String> returnFileList;
	
//	@Mock
	String fileLocationPath;
	
//	@Mock
	String outputFileLocation;
	
	@Mock
	List<Cmfoodchain> chainDetails;
	ObjectMapper mapper;
	
	@Mock
	ReportEngine reportEngine;
	
	@Mock
	Main main;
	
//	@Mock
//	Cmfoodchain chain1;
//	@Mock
//	Cmfoodchain chain2;
	
	@Mock
	Map<String, List<Branch>> groupedBranches;
	
	@Before
	public void setUp() throws Exception {
		
//		returnFileList = new HashSet<>();
//		returnFileList.add("BOM-1234-456.xml");
//		returnFileList.add("BOM-1234-457.json");
		
		fileLocationPath = "F:\\eclipsews\\TIAA\\TIAAReportEngine\\target\\classes\\inputfiles";
		outputFileLocation = "F:\\eclipsews\\TIAA\\TIAAReportEngine\\target\\classes\\outputfiles";
		
//		chainDetails.add(chain1);
//		chainDetails.add(chain2);
	}
	
	@Test(expected=ReportException.class) 
	public void testMainGetInputFileLocationForException() {
		
		doNothing().doThrow(ReportException.class).when(Main.class);
		Main.getInputFileLocation(null);
	}
	
	@Test(expected=ReportException.class) 
	public void testMainGetOutputFileLocationForException() {
		
		doNothing().doThrow(ReportException.class).when(Main.class);
		Main.getOutputFileLocation(null);
	}

	@Test(expected=Exception.class)
	public void testMainMethod() throws Exception {
		
		PowerMockito.mockStatic(Main.class);
		
		PowerMockito.when(Main.class, "getInputFileLocation", any(String.class)).thenReturn(fileLocationPath);
		PowerMockito.when(Main.class, "getOutputFileLocation", any(String.class)).thenReturn(fileLocationPath);
		doNothing().when(Main.class, "setReportEngine", any(ReportEngine.class));
		
		verifyStatic(Main.class);
		
		Main.getInputFileLocation("inputfiles");
		Main.getOutputFileLocation("outputfiles");
		Main.setReportEngine(reportEngine);
		
	}
	
	@Test
	public void testReportEngine() {
		
		when(reportEngine.getFileList(fileLocationPath)).thenReturn(returnFileList);
		when(reportEngine.readFiles(fileLocationPath, returnFileList)).thenReturn(chainDetails);
		when(reportEngine.processBranches(chainDetails)).thenReturn(groupedBranches);
		doNothing().when(reportEngine).writeToReportFile(outputFileLocation, groupedBranches);
		
		Set<String> returnFileListActual = reportEngine.getFileList(fileLocationPath);
		assertSame(returnFileList, returnFileListActual);
		
		List<Cmfoodchain> chainDetailActual = reportEngine.readFiles(fileLocationPath, returnFileList);
		assertSame(chainDetails, chainDetailActual);
		
		Map<String, List<Branch>> groupedBranchesActual = reportEngine.processBranches(chainDetails);
		assertSame(groupedBranches, groupedBranchesActual);
		
		reportEngine.writeToReportFile(outputFileLocation, groupedBranches);
		verify(reportEngine, times(1)).writeToReportFile(outputFileLocation, groupedBranches);
	}

//	@Test
//	public void testGetFileList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReadFiles() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testProcessBranch() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testProcessBranches() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testWriteToReportFile() {
//		fail("Not yet implemented");
//	}

}
