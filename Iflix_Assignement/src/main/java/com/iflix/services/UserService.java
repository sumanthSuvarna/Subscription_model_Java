package main.java.com.iflix.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.iflix.model.User;
import main.java.com.iflix.model.UserList;
import main.java.com.iflix.services.IUserService;

import java.io.File;
import java.util.List;

public class UserService implements IUserService {
	
	private String filePath;

    // Constructor
    public UserService(String filePath) {
        this.filePath = filePath;
    }

    public List<User> getAllUsers() throws Exception {
    	
    	 List<User> userList = null;

         try {

             ObjectMapper objectMapper = new ObjectMapper();

             UserList users = objectMapper.readValue(new File(filePath), UserList.class);

             if (users != null && users.getUsers() != null) {
                 userList = users.getUsers();
             }

         } catch (Exception e) {
             throw e;
         }

         return userList;

    }
}
