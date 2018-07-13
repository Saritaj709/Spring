package com.bridgelabz.fundonotes.module.controller;

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

import com.bridgelabz.fundonotes.module.confirmation.JwtToken;
import com.bridgelabz.fundonotes.module.exception.RegistrationException;
import com.bridgelabz.fundonotes.module.model.LoginDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.model.ResponseDTO;
import com.bridgelabz.fundonotes.module.model.User;
import com.bridgelabz.fundonotes.module.service.FundooService;

@RestController
@RequestMapping("/Fundoo")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private FundooService fundooService;

	@Autowired
	JwtToken jwtToken;

	@RequestMapping("/jwt")
	public ResponseEntity<String> jwtToken(@RequestBody User user) {

		String jwt = jwtToken.tokenGenerator(user);
		String token = jwtToken.parseJwtToken(jwt);
		return new ResponseEntity<>("User jwt details " + jwt + " token is " + token, HttpStatus.OK);

	}

	@RequestMapping("/users")
	public List<User> getAllUsers() {
		return fundooService.getAllUsers();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegistrationDTO user) throws RegistrationException {

		ResponseDTO response = new ResponseDTO();
		fundooService.saveUser(user);
		response.setMessage("User with email " + user.getEmail() + " registered successfully");
		response.setStatus(1);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> updateUser(@RequestBody User user) {
		ResponseDTO response = new ResponseDTO();
		if (fundooService.getUserByEmail(user.getEmail()) == false) {
			response.setMessage("User with " + user.getEmail() + " does not exist");
			response.setStatus(-1);
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		fundooService.updateUser(user);
		response.setMessage("User with email " + user.getEmail() + " successfully updated");
		response.setStatus(1);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> deleteUser(@RequestBody User user) {
		ResponseDTO response = new ResponseDTO();
		if (fundooService.getUserByEmail(user.getEmail()) == false) {
			response.setMessage("User with email " + user.getEmail() + " exists");
			response.setStatus(-1);
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		fundooService.deleteUser(user.getEmail());
		response.setMessage("User with email " + user.getEmail() + " successfully deleted");
		response.setStatus(1);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO user) {
		ResponseDTO response = new ResponseDTO();
		fundooService.loginUser(user);
		response.setMessage("User with email " + user.getEmail() + ", Sucessfully logged in");
		response.setStatus(2);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
