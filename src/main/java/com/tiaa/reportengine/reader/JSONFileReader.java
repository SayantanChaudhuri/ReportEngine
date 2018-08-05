package com.tiaa.reportengine.reader;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiaa.reportengine.model.input.json.Cmfoodchain;
import com.tiaa.reportengine.model.input.json.CmfoodchainRoot;

public class JSONFileReader implements Reader {

	private static JSONFileReader reader = new JSONFileReader();
	private JSONFileReader() {
		
	}
	
	public static JSONFileReader getInstance() {
		return reader;
	}

	@Override
	public Cmfoodchain readFile(String filePath) {
		
		Cmfoodchain r = null;
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			CmfoodchainRoot root = mapper.readValue(new File(filePath), CmfoodchainRoot.class);
			
			r = root.getCmfoodchain();
		} catch (IOException e) {
		}
		
		return r;
	}

	
	

}
