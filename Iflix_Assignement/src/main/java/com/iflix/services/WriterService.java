package main.java.com.iflix.services;

import java.io.FileWriter;

import org.json.JSONObject;

public class WriterService implements IWriterService{
	
	private String filePath;
	
	public WriterService(String path) {
		this.filePath = path;
	}

	@Override
	public void writeDataToOutputFile(JSONObject outputJsonObject) throws Exception {
		try {

            try (FileWriter file = new FileWriter(filePath)) {

                file.write(outputJsonObject.toString());

            }

        } catch (Exception e) {
            throw e;
        }
		
	}

}
