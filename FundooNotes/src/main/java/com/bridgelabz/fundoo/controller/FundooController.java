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

import com.bridgelabz.fundoo.exception.RegistrationException;
import com.bridgelabz.fundoo.jwt.JwtToken;
import com.bridgelabz.fundoo.model.LoginDTO;
import com.bridgelabz.fundoo.model.RegistrationDTO;
import com.bridgelabz.fundoo.model.ResponseDTO;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.service.FundooService;

@RestController
@RequestMapping("/Fundoo")
public class FundooController {

	public static final Logger logger = LoggerFactory.getLogger(FundooController.class);
	@Autowired
	private FundooService fundooService;
	
	@Autowired
	JwtToken jwtToken;
	@RequestMapping("/jwt")
	public ResponseEntity<String> jwtToken(@RequestBody User user){
		
		String jwt=jwtToken.tokenGenerator(user);//user.getEmail()+user.getPassword();
		String token=jwtToken.parseJwtToken(jwt);
		return new ResponseEntity<>("User jwt details "+jwt+" token is " +token, HttpStatus.OK);
		
	}
	
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		return fundooService.getAllUsers();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegistrationDTO user) throws RegistrationException {
	
		System.out.println("Welcome fundoo");
		
		ResponseDTO response=new ResponseDTO();
		System.out.println("registering");
		fundooService.saveUser(user);
		response.setMessage("User with email "+user.getEmail()+" registered successfully");
		response.setStatus(1);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
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
	public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO user) {
		ResponseDTO response=new ResponseDTO();
		fundooService.loginUser(user);
		response.setMessage("User with email "+user.getEmail()+"Sucessfully logged in");
		response.setStatus(2);
		/*if (fundooService.loginUser(user.getEmail(), user.getPassword()) == true) {
			return new ResponseEntity("Welcome to FundooApp " + user.getEmail(), HttpStatus.OK);
		}*/
		return new ResponseEntity<>(response,HttpStatus.OK);
		//return new ResponseEntity("User email or password is wrong " + user.getEmail(), HttpStatus.CONFLICT);
	}
}
