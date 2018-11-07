# java_assignment
Code assignment. Developer : Sumanth Suvarna,
Location : Kuala Lumpur
Email : sumanth.vicky@gmail.com
Contact : +60-126362400

Solution Overview

For this assignment , i have made use of Java as the primary programming language and Maven as build project tool(Depenendy libraies files managementa etc..) . For unit testing I have made use of JUNIT.

Application.java (main fucntion) 
       |---FileProcessorOrchestrator (chosen based on Factory pattern)
                    |---UserService (to get user accounts)
                    |---PartnerService (to get partner accounts)
                    |---SubscriptionService (to get subscriptions)
                    |---WriterService (to write output)
                                      
                              
                              
Our Program Starts with Application.java . Based on the input ( File , Or DB or API end points) we could decide which of the ProcessesOrchestrator to be called . And Input data will be processed there after. Since the Input data for our cases is in the form of JSON file ( accounts.json and partner files amazecom.json and wondertel.json) we make use of the filePrchestrator method.

Algorithm

1) Read account.json file , store each user information in a user object List. (UserService.java)
2) Read the partner files one by one and store them in list of partner object  (partnerService.java)
3) For each user read the List of partner files one by one.
          For Grants
       a)  if Grants is not null and if grants size is greater then 0 , Then Check if the grants period is greater then 0 and user account number matches with a grant account Number. if it matches add grant object along with its partner Name to GrantList.
       b) If Revocation is not null and if revocation size is greater then o. Check to see if the user account number matches any of revocation account number from list. any match add Revocation data along with its partner Name to revocationList.
  
 4) Sort the List of All grants based on the grant start date.( Grant List contains grant data of all partners for that particular user)
 5) Sort the list of All Revocation based on the Revocation Date..( Revocation List contains revocation data of all partners for that particular user)
 
 SubScription Service (Core Business Logic)
 
 6) For each user , If the Grant list is greater then 0. The First data from the Grant List gains the ownserfip of that account to grant the free subscription. i.r   (i==0).
  # Rule : The first partner to give a GRANT "owns" that user. Other partners cannot add to that users Offer. 
  
 7) For the First Grant Check if the revocation date comes in the range of 
       a) #Rule: if the Grant has been revoked By the Same Partner within the grant Date Range then the Revocation Date will the end Date/subcription End Date/Grant Expiry Date .
       b) #Rule :if the Grant has been revoked by the different Partner then it is Ignored.
 
 8) Add the subsequent grant to Subscription List along with the number of days between Grant Start date and subscription end Date . after implying the rule 7a and 7b.
 
 9) From the Second data from the Grant List Check in the subcription list.
         a) #Rule: if the Grant has been granted By the Same Partner within the grant Date Range then the period will stack up.
         b) #Rule :if the Grant has been granted by the different Partner within the grant Date Range then the Grant is Ignored.
         c) #Rule : if this Grant has been granted by the Same partner not within the grant Date Range then new subscription will be added to the subcription List for the same partner.
         d) #Rule : If this grant has been granted by other partner not within the grant Date range then new Subscription will be added to the subscription List for the current Partner
 Add the processed data to the subscription List         

(Note: Rule 9c & 9d works because we have sorted the grant list of all partner based on grant start date.)
         
10) Check for the revocation List if the revocation matches the partner name from the subscription List and is in the date range for the matches partner name.  Apply rule 7a & 7b for the subscription List.

11) After all the partner data is processed , add on the number of subscription days each partner has granted for the user. 
       
 
 Note:
 
 I did not make use of java 8 Streams for filtering the Collections data , Since it was taking more time in execution than the simple for loop .
 example : 
 List<Grant> grantUserList = grantList.stream().filter(grant ->  Long.toString(grant.getNumber()).equals("443584617453")).collect(Collectors.toList())
       
  
                              
                              

