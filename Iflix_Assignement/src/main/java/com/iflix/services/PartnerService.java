package main.java.com.iflix.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.iflix.exceptions.InvalidPartnerException;
import main.java.com.iflix.model.Partner;
import main.java.com.iflix.util.Constants;
import org.apache.log4j.Logger;

import java.io.File;

public class PartnerService implements IPartnerService{

	public Partner getPartnerData(String filePath) throws Exception {
	
	    Partner partner = null;
	
	    if (filePath != null && !filePath.isEmpty()) {
	        try {
	
	            ObjectMapper objectMapper = new ObjectMapper();
	            File partnerFile = new File(filePath);
	            String partnerName = partnerFile.getName().split("\\.")[0];
	            //String partnerName = partnerFileName; //todo : do filename from filepath without extension
	            
	            
	            partner = objectMapper.readValue(partnerFile, Partner.class);
	            partner.setName(partnerName);
	        } catch (Exception e) {
	
	            throw e;
	
	        }
	    }
	
	
	    return partner;
	
	}

}