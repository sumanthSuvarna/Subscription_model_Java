package main.java.com.iflix.services;

import java.io.FileWriter;

import org.json.JSONObject;

public class WriterService implements IWriterService{
	
	public WriterService() {
		
	}

	public void writeDataToOutputFile(String path, JSONObject outputJsonObject) throws Exception {
		try {

            try (FileWriter file = new FileWriter(path)) {

                file.write(outputJsonObject.toString());

            }

        } catch (Exception e) {
            throw e;
        }
		
	}

}
