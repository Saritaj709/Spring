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

import com.bridgelabz.login.model.User;
import com.bridgelabz.login.userservice.Service;

@RestController
@RequestMapping("/LoginRegister")
public class RegisterLoginController {

	public static final Logger logger = LoggerFactory.getLogger(RegisterLoginController.class);

	@Autowired
	private Service userService;

	/*
	 * @RequestMapping(value = "/login/", method = RequestMethod.POST) public
	 * ResponseEntity<String> login(@RequestBody User userVerify) throws
	 * ClassNotFoundException, SQLException { logger.info("Logging User : {}",
	 * userVerify); System.out.println("login here");
	 * 
	 * User user = userService.loginUser(userVerify.getEmail(),
	 * userVerify.getPassword()); if (user.getEmail() ==
	 * null||user.getPassword()==null) {
	 * logger.error("User with email {} not found.", user.getEmail()); return new
	 * ResponseEntity(new Utility("User with email " + userVerify.getEmail() +
	 * " not found"), HttpStatus.NOT_FOUND); } String message = "Hello, " +
	 * user.getFirstname() + "  "+ user.getLastname() + " Email:- " +
	 * user.getEmail() + " Phone Number:- " + user.getMobileNo(); return new
	 * ResponseEntity<String>(message, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/register/", method = RequestMethod.POST) public
	 * ResponseEntity<String> register(@RequestBody User user) throws
	 * ClassNotFoundException{ logger.info("Register user : {}", user);
	 * 
	 * boolean registered = userService.registerUser(user); if(!registered) {
	 * logger.error("User with email {} already present.", user.getEmail()); return
	 * new ResponseEntity(new Utility("User with email " + user.getEmail() +
	 * " already present"), HttpStatus.CONFLICT); }
	 * logger.info("User registered with : {}", user.getEmail()); String message =
	 * "Successfully registered"; return new ResponseEntity<String>(message,
	 * HttpStatus.OK);
	 */

	@RequestMapping("/users")
	public List<User> getEmployeeDetails() throws ClassNotFoundException {
		return userService.getUserDetails();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> loginUser(@RequestBody User user) throws ClassNotFoundException, SQLException {
		if (userService.getUserByEmail(user.getEmail())) {
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
