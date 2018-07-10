package com.bridgelabz.login.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.login.service.UserService;
import com.bridgelabz.model.User;

@RestController
@RequestMapping("/LoginRegister")
public class RegisterLoginController {

	public static final Logger logger = LoggerFactory.getLogger(RegisterLoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public List<User> getEmployeeDetails() throws ClassNotFoundException {
		return userService.getUserDetails();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> loginUser(@RequestBody User user) throws ClassNotFoundException, SQLException {
		if (userService.getUserByEmail(user.getEmail())==true) {
			return new ResponseEntity("User Allready Exist " + user.getEmail(), HttpStatus.CONFLICT);
		}
		userService.registerUser(user);
		return new ResponseEntity("User successfully Registered" + user.getEmail(), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> sigupUser(@RequestBody User user) throws ClassNotFoundException, SQLException {
		if (userService.loginUser(user.getEmail(), user.getPassword()) == true) {
			return new ResponseEntity("Welcome " + user.getEmail(), HttpStatus.OK);
		}
		return new ResponseEntity("User Name Or Password is incorrect " + user.getEmail(), HttpStatus.CONFLICT);
	}
}