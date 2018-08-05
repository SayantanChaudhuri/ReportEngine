package com.tiaa.reportengine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiaa.reportengine.exception.ReportException;
import com.tiaa.reportengine.model.input.json.Cmfoodchain;
import com.tiaa.reportengine.model.input.json.Orderdetail;
import com.tiaa.reportengine.model.input.json.Orders;
import com.tiaa.reportengine.model.output.json.Branch;
import com.tiaa.reportengine.model.output.json.CmfoodchainRoot;
import com.tiaa.reportengine.reader.ReaderFactory;

public class ReportEngine {

	private static DecimalFormat df2 = new DecimalFormat("#.00");
	private static String MISMATCHED_JSON_FILE = "Mismatched.json";
	private static String MATCHED_JSON_FILE = "Matched.json";
	private ObjectMapper mapper = new ObjectMapper();

	public ReportEngine(String fileLocationPath, String outputFileLocation) {

		System.out.println(fileLocationPath);
		System.out.println(outputFileLocation);
		
		Set<String> fileNames = getFileList(fileLocationPath);
		
		System.out.println(fileNames);
		
		List<Cmfoodchain> chainDetails = readFiles(fileLocationPath, fileNames);
		
		System.out.println(chainDetails);
		
		Map<String, List<Branch>> groupedBranches = processBranches(chainDetails);
		writeToReportFile(outputFileLocation, groupedBranches);
	}

	Set<String> getFileList(String filePath) {

		if (filePath == null || filePath.isEmpty()) {
			throw new ReportException("File Path is Empty");
		}

		try {
			Set<String> fileNames = Files.walk(Paths.get(filePath))
					.filter(fileName -> fileName.toString().endsWith(".xml") || fileName.toString().endsWith(".json"))
					.map(file -> file.getFileName().toString()).peek(System.out::println).sorted()
					.collect(Collectors.toSet());

			if (fileNames.isEmpty()) {
				throw new ReportException("No JSON or XML File exist in given path");
			}

			return fileNames;

		} catch (IOException e) {
			throw new ReportException(e.getMessage());
		}
	}

	List<Cmfoodchain> readFiles(String fileLocationPath, Set<String> fileNames) {

		return fileNames.stream().map(file -> ReaderFactory.getInstance().readFile(fileLocationPath, file))
				.collect(Collectors.toList());
	}

	Branch processBranch(Cmfoodchain inputChain) {
		Branch outputBranch = new Branch();

		com.tiaa.reportengine.model.input.json.Branch inputBranch = inputChain.getBranch();

		outputBranch.setLocation(inputBranch.getLocation());
		outputBranch.setTotalcollection(inputBranch.getTotalcollection());
		outputBranch.setLocationid(inputBranch.getLocationid());

		Orders orders = inputChain.getOrders();

		if (orders == null || orders.getOrderdetail() == null || orders.getOrderdetail().isEmpty()) {
			outputBranch.setSumoforder("0.00");
		} else {

			List<Orderdetail> orderList = orders.getOrderdetail();

			double sumoforder = orderList.stream().map(order -> order.getBillamount())
					.mapToDouble(billAmount -> Double.parseDouble(billAmount)).sum();

			outputBranch.setSumoforder(ReportEngine.df2.format(sumoforder));
		}

		return outputBranch;
	}

	Map<String, List<Branch>> processBranches(List<Cmfoodchain> chainDetails) {

		List<Branch> processedBranches = chainDetails.stream().map(chain -> processBranch(chain))
				.collect(Collectors.toList());

		Function<Branch, String> groupByFunction = (Branch t) -> t.getSumoforder().equalsIgnoreCase(t.getTotalcollection()) 
				? ReportEngine.MATCHED_JSON_FILE : ReportEngine.MISMATCHED_JSON_FILE; 

		return processedBranches.stream().collect(Collectors.groupingBy(groupByFunction));
	}
	
	void writeToReportFile(String fileLocationPath, Map<String, List<Branch>> groupedBranches) {
		
		if(groupedBranches == null) {
			try {
				Files.deleteIfExists(Paths.get(fileLocationPath+File.separator+ReportEngine.MATCHED_JSON_FILE));
				Files.deleteIfExists(Paths.get(fileLocationPath+File.separator+ReportEngine.MISMATCHED_JSON_FILE));
				
				Files.createFile(Paths.get(fileLocationPath+File.separator+ReportEngine.MATCHED_JSON_FILE));
				Files.createFile(Paths.get(fileLocationPath+File.separator+ReportEngine.MISMATCHED_JSON_FILE));
				
			} catch (IOException e) {
				throw new ReportException(e.getMessage());
			}
			
			return;
		}
		
		if (groupedBranches.get(ReportEngine.MISMATCHED_JSON_FILE) == null) {
			try {
				Files.deleteIfExists(Paths.get(fileLocationPath+File.separator+ReportEngine.MISMATCHED_JSON_FILE));
				Files.createFile(Paths.get(fileLocationPath+File.separator+ReportEngine.MISMATCHED_JSON_FILE));
			} catch (IOException e) {
				throw new ReportException(e.getMessage());
			}
		}
		else if (groupedBranches.get(ReportEngine.MISMATCHED_JSON_FILE) != null) {
			com.tiaa.reportengine.model.output.json.Cmfoodchain cmfoodChain = new com.tiaa.reportengine.model.output.json.Cmfoodchain();
			cmfoodChain.setBranch(groupedBranches.get(ReportEngine.MISMATCHED_JSON_FILE));
			CmfoodchainRoot root = new CmfoodchainRoot();
			root.setCmfoodchain(cmfoodChain);
			try {
				mapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(fileLocationPath+File.separator+ReportEngine.MISMATCHED_JSON_FILE), root);
			} catch (IOException e) {
				throw new ReportException(e.getMessage());
			}
		}
		
		if (groupedBranches.get(ReportEngine.MATCHED_JSON_FILE) == null) {
			try {
				Files.deleteIfExists(Paths.get(fileLocationPath+File.separator+ReportEngine.MATCHED_JSON_FILE));
				Files.createFile(Paths.get(fileLocationPath+File.separator+ReportEngine.MATCHED_JSON_FILE));
			} catch (IOException e) {
				throw new ReportException(e.getMessage());
			}
		}
		else if (groupedBranches.get(ReportEngine.MATCHED_JSON_FILE) != null) {
			com.tiaa.reportengine.model.output.json.Cmfoodchain cmfoodChain = new com.tiaa.reportengine.model.output.json.Cmfoodchain();
			cmfoodChain.setBranch(groupedBranches.get(ReportEngine.MATCHED_JSON_FILE));
			CmfoodchainRoot root = new CmfoodchainRoot();
			root.setCmfoodchain(cmfoodChain);
			try {
				mapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(fileLocationPath+File.separator+ReportEngine.MATCHED_JSON_FILE), root);
			} catch (IOException e) {
				throw new ReportException(e.getMessage());
			}
		}
	}
}
