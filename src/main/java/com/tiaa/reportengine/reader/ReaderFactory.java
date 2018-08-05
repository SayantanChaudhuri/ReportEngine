package com.tiaa.reportengine.reader;

import java.io.File;

import com.tiaa.reportengine.model.input.json.Cmfoodchain;

enum FileType {
	XML, JSON;
}

public class ReaderFactory {

	private static ReaderFactory readerFactory;

	static {
		readerFactory = new ReaderFactory();
	}

	private ReaderFactory() {

	}

	public static ReaderFactory getInstance() {
		return readerFactory;
	}

	public Cmfoodchain readFile(String filePath, String fileName) {

		String fileFullPath = filePath + File.separator + fileName;

		Cmfoodchain result = null;

		FileType fType = FileType.valueOf(fileName.endsWith(".xml")? "XML" : "JSON");

		switch (fType) {

		case XML:
			result = XMLFileReader.getInstance().readFile(fileFullPath);
			break;

		case JSON:
			result = JSONFileReader.getInstance().readFile(fileFullPath);
			break;
		}

		return result;

	}
}
