package main.java.com.iflix.businessProcessors;

import java.io.File;

import main.java.com.iflix.exceptions.IflixException;
import main.java.com.iflix.model.form.FileFormModel;
import main.java.com.iflix.model.form.IFormModel;

public class FileProcessorOrchestrator implements IProcessorOrchestrator {

	@Override
	public boolean Process(IFormModel inputObj) throws IflixException {
		
		FileFormModel model = (FileFormModel)inputObj;
		
		if(model == null)
			throw new IflixException("Wrong model object");				
			
		File file = new File(model.getSourceFileName());
		if(!file.exists())
			throw new IflixException("Source File not found or invalid path");
		
		
		
		
		return true;
	}

}
