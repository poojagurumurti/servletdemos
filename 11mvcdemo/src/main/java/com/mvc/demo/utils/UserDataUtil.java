package com.mvc.demo.utils;

import java.util.ArrayList;
import java.util.List;

import com.mvc.demo.model.User;

public class UserDataUtil {
	public static List<User> getUsers(){
		List<User> users=new ArrayList<>();
		users.add(new User("Pooja","Gurumurti","ptam@gmail.com","1234"));
		users.add(new User("Tota","Sajjanar","tota@gmail.com","1237"));
		users.add(new User("Trigun","Tamragouri","ttam@gmail.com","1239"));
		users.add(new User("Kavya","Dugnur","kd@gmail.com","1238"));
		return users;
	}

}


//Util gives the list of object,that object is sent into the request variable using RequestDispatcher->sent
//it to the JSP