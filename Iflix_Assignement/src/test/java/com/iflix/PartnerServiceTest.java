package test.java.com.iflix;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.core.JsonParseException;

import main.java.com.iflix.model.Grant;
import main.java.com.iflix.model.Partner;
import main.java.com.iflix.model.Revocation;
import main.java.com.iflix.model.User;
import main.java.com.iflix.services.IPartnerService;
import main.java.com.iflix.services.PartnerService;

public class PartnerServiceTest extends BaseTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testGetPartnerDataForWrongFilePath() throws Exception {
	    expectedEx.expect(FileNotFoundException.class);
		IPartnerService partnerService = new PartnerService();
		partnerService.getPartnerData("testPartner.json");
	}
	
	
	@Test
	public void testWrongJSONFile()throws Exception { 
		expectedEx.expect(JsonParseException.class);
		IPartnerService partnerService = new PartnerService();
		partnerService.getPartnerData("pom.xml");
	}
	
	// Similarily you could test for IOException and JSONMapping Exception.
	
	@Test
	public void correctPartnerJSONFile() {
		
		//Arrange
				String fileName = "partner1.json";
				String contents = "{\r\n" + 
						"  \"revocations\": [\r\n" + 
						"    {\r\n" + 
						"      \"number\": \"30969230305\",\r\n" + 
						"      \"date\": \"2015-09-04T09:04:24+00:00\"\r\n" + 
						"    }\r\n" + 
						"  ],\r\n" + 
						"  \"grants\": [\r\n" + 
						"    {\r\n" + 
						"      \"period\": 2,\r\n" + 
						"      \"number\": \"77902601451\",\r\n" + 
						"      \"date\": \"2015-02-21T15:10:01+00:00\"\r\n" + 
						"    },\r\n" + 
						"    {\r\n" + 
						"      \"period\": 0,\r\n" + 
						"      \"number\": \"2585076194\",\r\n" + 
						"      \"date\": \"2015-10-16T22:24:24+00:00\"\r\n" + 
						"    }\r\n" + 
						"  ]\r\n" + 
						"}\r\n" + 
						"";			
				super.createFile(fileName, contents);		
				String path = super.GetFileWithPath(fileName);
				
				List<Partner> expectedList = new ArrayList<>();
				Partner partner = new Partner();
				List<Revocation> revocationList  = new ArrayList<Revocation>();
				List<Grant> grantList = new ArrayList<Grant>();
				Revocation revocation = new Revocation();
				Grant grant =  new Grant();
				
				partner.setName("amazecom");
				revocation.setDate(new Date("2015-09-04T09:04:24+00:00"));
				revocation.setNumber(30969230305L);
				revocation.setPartner("amazecom");
				revocationList.add(revocation);
				grant.setDate(new Date("2015-09-04T09:04:24+00:00"));
				grant.setPeriod(2);
				grant.setNumber(77902601451L);
				grantList.add(grant);
				grant =  new Grant();
				grant.setDate(new Date("2015-10-16T22:24:24+00:00"));
				grant.setPeriod(0);
				grant.setNumber(2585076194L);	
				grantList.add(grant);
				
	}

}
