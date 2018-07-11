package com.bridgelabz.fundoo.controller;

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

import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.service.FundooService;

@RestController
@RequestMapping("/Fundoo")
public class FundooController {

	public static final Logger logger = LoggerFactory.getLogger(FundooController.class);
	@Autowired
	private FundooService fundooService;
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		return fundooService.getAllUsers();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		if (fundooService.getUserByEmail(user.getEmail()) == true) {
			return new ResponseEntity("User with " + user.getEmail() + " already exists", HttpStatus.CONFLICT);
		}
		fundooService.saveUser(user);
		return new ResponseEntity("User with email " + user.getEmail() + " successfully registered", HttpStatus.OK);
	}
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		if (fundooService.getUserByEmail(user.getEmail()) == false) {
			return new ResponseEntity("User with " + user.getEmail() + " does not exist", HttpStatus.CONFLICT);
		}
		fundooService.updateUser(user);
		return new ResponseEntity("User with email " + user.getEmail() + " successfully updated", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@RequestBody User user) {
		if (fundooService.getUserByEmail(user.getEmail()) == false) {
			return new ResponseEntity("User with " + user.getEmail() + " exists", HttpStatus.CONFLICT);
		}
		fundooService.deleteUser(user.getEmail());
		return new ResponseEntity("User with email " + user.getEmail() + " successfully deleted", HttpStatus.OK);
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> loginUser(@RequestBody User user) {
		if (fundooService.loginUser(user.getEmail(), user.getPassword()) == true) {
			return new ResponseEntity("Welcome to FundooApp " + user.getEmail(), HttpStatus.OK);
		}
		return new ResponseEntity("User email or password is wrong " + user.getEmail(), HttpStatus.CONFLICT);
	}
}
