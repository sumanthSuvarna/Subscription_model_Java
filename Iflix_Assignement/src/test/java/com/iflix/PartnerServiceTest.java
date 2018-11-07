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
	
	

}
