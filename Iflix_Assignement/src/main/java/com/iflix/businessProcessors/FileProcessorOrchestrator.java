package main.java.com.iflix.businessProcessors;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

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
		
		System.out.println("Processing begins with FileProcessorOrchestrator...");
		
		//Validate inputs and throw exceptions ; check for file exits - inputsource and partner files
		
		//User service to get all users
		FileFormModel model = (FileFormModel)inputObj;			
		IUserService userService = new UserService();
		System.out.println("Getting all users started");
		List<User> userList = userService.getAllUsers(model.getSourceFileName());
		System.out.println("Getting all users ended");
		
		//Partner service to get all partners
		List<Partner> partnerList = new ArrayList<Partner>();
		String[] partnerFileNames = model.getPartnerFileNames();	
		System.out.println("Getting all partners started");
		for(int i=0; i< partnerFileNames.length; i++)
		{
			IPartnerService partnerService = new PartnerService();
			Partner partner =  partnerService.getPartnerData(partnerFileNames[i]);
			partnerList.add(partner);
		}
		System.out.println("Getting all partners ended");
		
		//Subscription service to get all subscriptions
		ISubscriptionService subscriptionService = new SubscriptionService();
		System.out.println("Processing subscription details started");
		JSONObject outputJsonObject = subscriptionService.getSubscriptionJsonObject(userList, partnerList);
		System.out.println("Processing subscription details ended");
		
		//Writer service to write the output
		IWriterService writerService = new WriterService();
		System.out.println("Writing subscription details");
		writerService.writeDataToOutputFile(model.getDestFileName(), outputJsonObject);
		
		return true;
	}

}
