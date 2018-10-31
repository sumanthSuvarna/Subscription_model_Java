package test.java.com.iflix;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.java.com.iflix.exceptions.IflixException;
import main.java.com.iflix.model.User;
import main.java.com.iflix.services.IUserService;
import main.java.com.iflix.services.UserService;
import main.java.com.iflix.util.Constants;

public class UserServiceTest {

	@Test
	public void testGetAllUsers() {
		
		/*
		//Arrange, Execute and Assert
		IUserService userService = new UserService("C:\\ttt\test.json");
		Aseert.ExpectsExceptionWithMessage(userService.getAllUsers(), IflixException ex, Constants.USER_SERVICE_MESSAGE.INVALID_FILE_PATH, ex.customMsg);
		
		
		IUserService userService = new UserService("sfdjskfkjfkejf");
		Aseert.ExpectsExceptionWithMessage(userService.getAllUsers(), IflixException ex, Constants.USER_SERVICE_MESSAGE.INVALID_FILE_PATH, ex.customMsg);
	
		
		IUserService userService = new UserService("C:\\djdjd.json");
		Aseert.ExpectsExceptionWithMessage(userService.getAllUsers(), IflixException ex, Constants.USER_SERVICE_MESSAGE.INCORRECT_JSON_FORMAT, ex.customMsg);
	
		
		//Arrange
		IList<User> expected = new List<Users>();
		String validFileName = "C:\\valid.json";
		//Execute
		IUserService userService = new UserService("C:\\valid.json");
		IList<Users> usersActual =   userService.getAllUsers();
		//Assert
		Assert.AreEqual(usersActual, usersExpected );
		
		
		
		//MockService.Mock(IList<Users>
		
		//Arrange
			/*	IList<User> expected = new List<Users>();
				String validFileName = "C:\\valid.json";
				//Execute
				IUserService userService = new UserService("C:\\valid.json");
				IMockObject = new MockObject<UserService>().whenExecute.GetAllUsers().returnMe(new Users() list );
				IList<Users> usersActual = IMockObject.getAllUsers();
				//Assert
				Assert.AreEqual(usersActual, usersExpected );*/
	
		
		
		//fail("Not yet implemented");*/
	}
	

}
