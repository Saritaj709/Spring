package com.bridgelabz.usercontroller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;

@RestController
public class Userlist {
	List<User> list = new LinkedList<>();

	@RequestMapping("/user")
	public List userList() {
		User user = new User();
		user.setName("pratik");
		user.setEmail("pratikpr@gmail.com");
		list.add(user);
		user.setName("joy");
		user.setEmail("jayant@gmail.com");
		list.add(user);
		return list;
	}
}
