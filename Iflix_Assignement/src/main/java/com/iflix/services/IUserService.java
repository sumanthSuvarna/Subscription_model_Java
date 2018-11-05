package main.java.com.iflix.services;

import java.util.List;
import main.java.com.iflix.model.User;

public interface IUserService {

	List<User> getAllUsers(String filePath) throws Exception;	
	
}
