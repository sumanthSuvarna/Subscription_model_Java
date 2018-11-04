package main.java.com.iflix.services;
import main.java.com.iflix.model.*;
import main.java.com.iflix.util.Constants;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SubscriptionService implements ISubscriptionService {
	
	private List<User> userList;
	private List<Partner> partnerList;
	
	public SubscriptionService(List<User> userList, List<Partner> partnerList)
	{
		this.userList = userList;
		this.partnerList = partnerList;
	}

    public JSONObject getSubscriptionJsonObject() {

        JSONObject outputJsonObject = new JSONObject();
        JSONArray subscriptionJson = new JSONArray();

        for (User user : userList) {

            // Calculate subscription
            JSONObject userSubscription = doSubscriptionProcessing(user);

            if (userSubscription != null) {
                subscriptionJson.put(userSubscription);
            }

        }

        // Display final output
        outputJsonObject.put("subscriptions", subscriptionJson);
        return outputJsonObject;
    }
    
    private JSONObject doSubscriptionProcessing(User user) {

        AllUserSubscription allUserSubscription = getSubscriptionForUser(user);
        // Sorted Grants List For users 
        List<Grant> grantList = allUserSubscription.getGrantList();
        //Sorted Revocation List For users 
        List<Revocation> revocationList = allUserSubscription.getRevocations();        
		
		List<Subcription> subcriptionList = new ArrayList<Subcription>();

        JSONObject partners = null;
        
        if (grantList.size() > 0) {
            
        	for(int i=0;i< grantList.size(); i++) {
				
        		Grant grant = grantList.get(i);
				Date grantStartDate = grant.getDate();
				String partnerName = grant.getPartner();
			
				// Rule: The first partner to give a GRANT "owns" that user. Other partners cannot add to that users Offer.
				if(i==0){					
					
					//Check if the Grant has been revoked By the Same Partner within the grant Date Range
					if (revocationList.size() > 0) {
						for(Revocation revoke : revocationList) {
					         
							// Rule: A partner can only revoke an Offer if they "own" the user.
							
							if(revoke.getPartner().equals(partnerName)){
								Date revokeDate = revoke.getDate();
								if((revokeDate.before(grant.getExpireDate()) && revokeDate.after(grantStartDate))) {
									grant.setExpireDate(revokeDate); 									
								}
							}											
						}	
					}
					long diff = grant.getExpireDate().getTime() - grant.getDate().getTime();
				    int noOfDaysL = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);				   
					Subcription subcription = new Subcription();
					subcription.setStartDate(grant.getDate());
					subcription.setEndDate(grant.getExpireDate());
					subcription.setPartnerName(partnerName);
					subcription.setNumberOfDays(noOfDaysL);
					//grant.setSubscribed(true);
					subcriptionList.add(subcription);

					
				}
				// Loop Through the Other Grants
				else {
					
					// Check if Grant Date is in range with the subscription Date
					//Loop through the subscription
					 Subcription subcription = subcriptionList.get(subcriptionList.size()-1);
						 
					 //Check to see if Grant Date is in date Range with Subscrption Date
					 // If Yess Add Up the period To subscription End Date
					 if(subcription.getPartnerName().equals(partnerName)){
						 if((grantStartDate.before(subcription.getEndDate()) && grantStartDate.after(subcription.getStartDate()))) {
							 subcription.setEndDate(new DateTime(subcription.getEndDate()).plusMonths(grant.getPeriod()).toDate());
							 subcription.setPartnerName(partnerName);
						     long diff = subcription.getEndDate().getTime() - subcription.getStartDate().getTime();
						     int noOfDaysL = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
							 subcription.setNumberOfDays(noOfDaysL);
							 subcriptionList.remove(subcriptionList.size()-1);
							 subcriptionList.add(subcription);
						 }	
						 // IF not in Date Range Check the Next Partners First Grants Date and Compare
						 else{
							long diff = grant.getExpireDate().getTime() - grant.getDate().getTime();
						    int noOfDaysL = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);						    
							Subcription subcriptionNew = new Subcription();
							subcriptionNew.setNumberOfDays(noOfDaysL);
							subcriptionNew.setStartDate(grant.getDate());
							subcriptionNew.setEndDate(grant.getExpireDate());
							subcriptionNew.setPartnerName(partnerName);
							subcriptionList.add(subcriptionNew);
						 }							 
					 }
					 
					 // IF different Partners
					 // Ignore The Grant if it falls in the same range
					 else{
						 if(!(grantStartDate.before(subcription.getEndDate()) && grantStartDate.after(subcription.getStartDate()))) {
								long diff = grant.getExpireDate().getTime() - grant.getDate().getTime();
							    int noOfDaysL = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
								Subcription subcriptionNew = new Subcription();
								subcriptionNew.setStartDate(grant.getDate());
								subcriptionNew.setEndDate(grant.getExpireDate());
								subcriptionNew.setNumberOfDays(noOfDaysL);
								subcriptionNew.setPartnerName(partnerName);
								subcriptionList.add(subcriptionNew);
						 }	
					 }
					
					
					// if not the First Element
					//Check if the Grant has been revoked , If yes Update the exoiry Date
					for(Revocation revoke : revocationList) {
						if(revoke.getPartner().equals(partnerName)){
							Date revokeDate = revoke.getDate();
							if((revokeDate.before(grant.getExpireDate()) && revokeDate.after(grantStartDate))) {
								subcriptionList.remove(subcriptionList.size()-1);
								grant.setExpireDate(revokeDate); 
								long diff = grant.getExpireDate().getTime() - grant.getDate().getTime();
							    int noOfDaysL = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);	
								subcription.setStartDate(grant.getDate());
								subcription.setEndDate(grant.getExpireDate());
								subcription.setPartnerName(partnerName);
								subcription.setNumberOfDays(noOfDaysL);
								subcriptionList.add(subcription);
							}	
						}						
					}	
				
				}
        	}

            partners = new JSONObject();        	
			Set <String> partnerSet =  new HashSet<String>();
			for(Subcription subcription : subcriptionList) {
				 partnerSet.add(subcription.getPartnerName());
			}
			
			for(String individualPartner: partnerSet) {	
				int subcriptionCount =0;
				for(Subcription subcription : subcriptionList){
					if(subcription.getPartnerName().equalsIgnoreCase(individualPartner)){						
						subcriptionCount = subcriptionCount+subcription.getNumberOfDays();
					}
				}
				if(subcriptionCount>0) {
					partners.put(individualPartner, subcriptionCount);
				}
			}

            JSONObject user1 = new JSONObject();
            user1.put(user.getName(), partners);
            return user1;
        	
        } else {
            return null;
        }

    }

    private AllUserSubscription getSubscriptionForUser(User user) {

        AllUserSubscription allUserSubscription = new AllUserSubscription();
        allUserSubscription.setName(user.getName());
        allUserSubscription.setNumber(user.getNumber());

        List<Grant> grantList = new ArrayList<>();
        List<Revocation> revocationList = new ArrayList<>();
        
        for(int i = 0 ; i < partnerList.size(); i++)
        {
        	List<Grant> partnerGrants = partnerList.get(i).getGrants();
            List<Revocation> partnerRevocations = partnerList.get(i).getRevocations();

            if (partnerGrants != null && partnerGrants.size() > 0) {
                for (Grant grant : partnerGrants) {

                    if (grant.getNumber() == user.getNumber() && grant.getPeriod() > 0) {

                        grant.setPartner(partnerList.get(i).getName());
                        grant.setExpireDate(new DateTime(grant.getDate()).plusMonths(grant.getPeriod()).toDate());
                        grantList.add(grant);

                    }

                }

            }

            if (partnerRevocations != null && partnerRevocations.size() > 0) {
                for (Revocation revocation : partnerRevocations) {

                    if (revocation.getNumber() == user.getNumber()) {
                        revocation.setPartner(partnerList.get(i).getName());
                        revocationList.add(revocation);
                    }

                }

            }
        }       
        
        
        allUserSubscription.setGrantList(grantList);
        allUserSubscription.setRevocations(revocationList);
        return allUserSubscription;
    }
  
}