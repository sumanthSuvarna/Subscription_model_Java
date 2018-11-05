package test.java.com.iflix;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.core.JsonParseException;

import main.java.com.iflix.model.User;
import main.java.com.iflix.services.IUserService;
import main.java.com.iflix.services.UserService;

public class UserServiceTest extends BaseTest {
	
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testGetAllUsersForWrongFilePath() throws Exception {
	    expectedEx.expect(FileNotFoundException.class);
		IUserService userService = new UserService();
		userService.getAllUsers("test.json");
	}
	
	@Test
	public void testWrongJSONFile()throws Exception { 
		expectedEx.expect(JsonParseException.class);
		IUserService userService = new UserService();
		userService.getAllUsers("pom.xml");
	}
	
	// Similarily you could test for IOException and JSONMapping Exception.
	
	@Test
	public void testCorrectFile() { 
		
		//Arrange
		String fileName = "users1.json";
		String contents = "{\r\n" + 
				"  \"users\": [\r\n" + 
				"    {\r\n" + 
				"      \"number\": \"45875660609\",\r\n" + 
				"      \"name\": \"John\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"number\": \"49509330262\",\r\n" + 
				"      \"name\": \"Jenny\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";			
		super.createFile(fileName, contents);		
		String path = super.GetFileWithPath(fileName);
		
		List<User> expectedList = new ArrayList<>();
		User usr = new User();
		usr.setName("John");
		usr.setNumber(45875660609L);
		expectedList.add(usr);
		usr = new User();
		usr.setName("Jenny");
		usr.setNumber(49509330262L);
		expectedList.add(usr);
		
		//Act and Assert
		try{
			IUserService userService = new UserService();
			List<User> actualList =   userService.getAllUsers(path);
			Assert.assertEquals(expectedList.size(), actualList.size());			
			Assert.assertEquals(expectedList.get(0).getName(), actualList.get(0).getName());
			Assert.assertEquals(expectedList.get(1).getName(), actualList.get(1).getName());
			Assert.assertEquals(expectedList.get(0).getNumber(), actualList.get(0).getNumber());
			Assert.assertEquals(expectedList.get(1).getNumber(), actualList.get(1).getNumber());			
			
			// Check if the account number is of 11 Digit
			for(User user: expectedList) {
				if(String.valueOf(user.getNumber()).length()!=11) {
					Assert.fail("Account Number :"+user.getNumber()+" for user: "+user.getName()+" is invalid");
				}
			}
						
			//Assert.assertFalse("Invalid Account Number", condition);
		}
		catch(Exception ex){
			Assert.fail("Exception while calling userlist or some error");
		}
		super.tearDown();
	}

}
