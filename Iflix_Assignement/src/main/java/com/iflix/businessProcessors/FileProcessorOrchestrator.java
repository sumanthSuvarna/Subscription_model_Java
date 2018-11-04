package main.java.com.iflix.businessProcessors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import main.java.com.iflix.exceptions.IflixException;
import main.java.com.iflix.model.Partner;
import main.java.com.iflix.model.User;
import main.java.com.iflix.model.form.FileFormModel;
import main.java.com.iflix.model.form.IFormModel;
import main.java.com.iflix.services.IPartnerService;
import main.java.com.iflix.services.ISubscriptionService;
import main.java.com.iflix.services.IUserService;
import main.java.com.iflix.services.IWriterService;
import main.java.com.iflix.services.PartnerService;
import main.java.com.iflix.services.SubscriptionService;
import main.java.com.iflix.services.UserService;
import main.java.com.iflix.services.WriterService;

public class FileProcessorOrchestrator implements IProcessorOrchestrator {

	@Override
	public boolean Process(IFormModel inputObj) throws Exception {
		
		//Validate inputs and throw exceptions ; check for file exits - inputsource and partner files
		
		//User service to get all users
		FileFormModel model = (FileFormModel)inputObj;			
		IUserService userService = new UserService(model.getSourceFileName());
		List<User> userList = userService.getAllUsers();
		
		//Partner service to get all partners
		List<Partner> partnerList = new ArrayList<Partner>();
		String[] partnerFileNames = model.getPartnerFileNames();		
		for(int i=0; i< partnerFileNames.length; i++)
		{
			IPartnerService partnerService = new PartnerService(partnerFileNames[i]);
			Partner partner =  partnerService.getPartnerData();
			partnerList.add(partner);
		}
		
		//Subscription service to get all subscriptions
		ISubscriptionService subscriptionService = new SubscriptionService(userList, partnerList);
		JSONObject outputJsonObject = subscriptionService.getSubscriptionJsonObject();
		
		
		//Writer service to write the output
		IWriterService writerService = new WriterService(model.getDestFileName());
		writerService.writeDataToOutputFile(outputJsonObject);
		
		return true;
	}

}
