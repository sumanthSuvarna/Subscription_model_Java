package main.java.com.iflix.services;

import main.java.com.iflix.model.Partner;

public interface IPartnerService {
	
	Partner getPartnerData(String filePath) throws Exception;

}
