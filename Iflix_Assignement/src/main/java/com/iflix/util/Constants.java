package main.java.com.iflix.util;

public class Constants {
	
	public interface MAIN {
		String FILE_PROCESSOR_TYPE = "FILEPROCESSOR";
		String DATABASE_PROCESSOR_TYPE = "DATABASEPROCESSOR";
		String API_PROCESSOR_TYPE = "APIPROCESSOR";
		
		String LOGGING_PATH = "$ProjectDir/out[put/error.log";
	}

    public interface USER {
        String DATA_LOCATION = "/data/accounts.json";
    }

    public interface OUTPUT {
        String FILE_LOCATION = "/output/result.json";
    }

    public interface PARTNER_PATH {
        String AMAZECOM = "/data/amazecom.json";
        String WONDERTEL = "/data/wondertel.json";
    }

    public interface PARTNER {
        String AMAZECOM = "amazecom";
        String WONDERTEL = "wondertel";
    }
    
    public interface USER_SERVICE_MESSAGE
    {
    	String INVALID_FILE_PATH = "File path is wrong";
    	String INCORRECT_JSON_FORMAT = "Incorrect JSON format";
    }

}