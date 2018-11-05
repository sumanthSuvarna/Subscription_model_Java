package main.java.com.iflix.services;

import org.json.JSONObject;

public interface IWriterService {
	void writeDataToOutputFile(String path, JSONObject outputJsonObject) throws Exception;
}
