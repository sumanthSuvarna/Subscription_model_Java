package main.java.com.iflix.services;

import org.json.JSONObject;

public interface IWriterService {
	void writeDataToOutputFile(JSONObject outputJsonObject) throws Exception;
}
