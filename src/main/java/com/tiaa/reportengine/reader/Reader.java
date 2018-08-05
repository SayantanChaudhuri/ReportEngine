package com.tiaa.reportengine.reader;

import com.tiaa.reportengine.model.input.json.Cmfoodchain;

public interface Reader {

	Cmfoodchain readFile(String filePath);
}
