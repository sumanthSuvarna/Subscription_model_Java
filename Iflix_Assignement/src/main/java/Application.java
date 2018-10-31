package main.java;

import org.apache.log4j.Logger;

import main.java.com.iflix.businessProcessors.APIProcessorOrchestratorForFuture;
import main.java.com.iflix.businessProcessors.DBProcessorOrchestratorForFuture;
import main.java.com.iflix.businessProcessors.FileProcessorOrchestrator;
import main.java.com.iflix.businessProcessors.IProcessorOrchestrator;
import main.java.com.iflix.exceptions.IflixException;
import main.java.com.iflix.model.form.FileFormModel;
import main.java.com.iflix.util.Constants;

public class Application {

	private final static Logger LOGGER = Logger.getLogger(Application.class);
	
	public static void main(String[] args) {
		String typeOfProcessor = Constants.MAIN.FILE_PROCESSOR_TYPE;
		FileFormModel iModel = new FileFormModel();
		iModel.setSourceFileName(Constants.USER.DATA_LOCATION);
		iModel.setDestFileName(Constants.OUTPUT.FILE_LOCATION);
						
		try 
		{
			IProcessorOrchestrator processor = GetProcessorFromFactory(typeOfProcessor);
			processor.Process(iModel);
		}
		catch(IflixException iex)
		{
			LOGGER.error("Error stack trace : " + iex.toString());	
			System.out.println("Error occured. Contact support!" + iex.customMsg);
		}
		catch(Exception ex)
		{		
			LOGGER.error("Error stack trace : " + ex.toString());	
			System.out.println("Unknown Error occured. Contact support!");
		}		
		finally {
					
		}
	}
	
	/*
	 * This is the factory method which returns the type of BusinessProcessor.
	 * Currently IFlix needs FileProcessor only.
	 * The program can extend to other types needed in future.
	 */
	private static IProcessorOrchestrator GetProcessorFromFactory(String processorType)
	{
		switch(processorType)
		{
			case Constants.MAIN.FILE_PROCESSOR_TYPE:
				return new FileProcessorOrchestrator();
			case Constants.MAIN.DATABASE_PROCESSOR_TYPE:
				//nothing here - for future	
				return new DBProcessorOrchestratorForFuture();
			case Constants.MAIN.API_PROCESSOR_TYPE:
				//nothing here - for future
				return new APIProcessorOrchestratorForFuture();
			default:
				return new FileProcessorOrchestrator();
		}	
	}
}
