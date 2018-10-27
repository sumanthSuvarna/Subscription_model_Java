package main.java.com.iflix.businessProcessors;

import main.java.com.iflix.model.Result;
import javax.json.JsonObject;



public interface IProcessors {

	public Result Process(JsonObject inputObj);
	
}
