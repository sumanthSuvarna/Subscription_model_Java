package test.java.com.iflix;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.iflix.model.User;
import main.java.com.iflix.services.UserService;

public class UserAccountTest {

	@Test
	public void testGetUsers() {
		UserService userService = new UserService();
		int size = userService.getUsers().size();
		Assert.assertEquals(size, 11);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testAccountNumber() {
		UserService userService = new UserService();
		List<User> userList = userService.getUsers();
		int size;
		for(User user : userList) {
			System.out.println(user.getNumber());
			size = (int) (user.getNumber()+"".length());			
			Assert.assertEquals(size, 11);
		}		
	}
	

}
