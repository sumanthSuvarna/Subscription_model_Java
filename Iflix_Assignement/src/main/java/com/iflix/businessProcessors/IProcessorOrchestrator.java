package main.java.com.iflix.businessProcessors;

import main.java.com.iflix.exceptions.IflixException;
import main.java.com.iflix.model.form.IFormModel;

public interface IProcessorOrchestrator {

	public boolean Process(IFormModel iModel) throws IflixException;
	
}
