package com.tiaa.reportengine.reader;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiaa.reportengine.exception.ReportException;
import com.tiaa.reportengine.model.input.json.Cmfoodchain;

public class XMLFileReader implements Reader {

	private static XMLFileReader reader  = new XMLFileReader();
	
	private XMLFileReader() {
	}

	public static XMLFileReader getInstance() {
		
		return reader;
	}
	
	public Cmfoodchain readFile(String filePath) {
		
		try {
			
			File file = new File(filePath);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(com.tiaa.reportengine.model.input.xsd.Cmfoodchain.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			com.tiaa.reportengine.model.input.xsd.Cmfoodchain r = (com.tiaa.reportengine.model.input.xsd.Cmfoodchain) unmarshaller
					.unmarshal(file);
			
			ObjectMapper mapper = new ObjectMapper();

			String jsonStr = mapper.writeValueAsString(r);
			
			Cmfoodchain r1 = mapper.readValue(jsonStr, Cmfoodchain.class);
			System.out.println(r1);
			return r1;
			
		} catch (JAXBException | IOException e) {
			throw new ReportException(e.getMessage());
		}
	}
}
