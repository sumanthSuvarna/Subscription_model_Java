package main.java.com.iflix.services;

import java.util.List;

import org.json.JSONObject;

import main.java.com.iflix.model.Partner;
import main.java.com.iflix.model.User;

public interface ISubscriptionService {

	JSONObject getSubscriptionJsonObject(List<User> userList, List<Partner> partnerList);
}
