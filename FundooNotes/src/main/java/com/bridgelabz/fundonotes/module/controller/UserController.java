package com.bridgelabz.fundonotes.module.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundonotes.module.exception.RegistrationException;
import com.bridgelabz.fundonotes.module.model.PasswordDTO;
import com.bridgelabz.fundonotes.module.model.LoginDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.model.ResponseDTO;
import com.bridgelabz.fundonotes.module.model.User;
import com.bridgelabz.fundonotes.module.service.FundooService;
import com.bridgelabz.fundonotes.module.service.UserRepository;

@RestController
@RequestMapping("/Fundoonotes")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private FundooService fundooService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	ResponseDTO response;

	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<User> getAllUsers() {
		return fundooService.getAllUsers();
	}
	
	@RequestMapping(value="/activateaccount",method=RequestMethod.GET)
	public ResponseEntity<ResponseDTO> activateAccount(HttpServletRequest request) throws RegistrationException{
		String token=request.getQueryString();
		System.out.println(token);
		if(fundooService.activateJwt(token)) {
			response.setMessage("Account activated successfully");
			response.setStatus(1);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setMessage("Account not yet activated");
			response.setStatus(-1);
			return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegistrationDTO user) throws RegistrationException {

		fundooService.saveUser(user);
		response.setMessage("User with email " + user.getEmail() + " registered successfully");
		response.setStatus(1);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> deleteUser(@RequestBody User user) {
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
	public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO user,HttpServletRequest res) {
		fundooService.loginUser(user);
		response.setMessage("User with email " + user.getEmail() + ", Sucessfully logged in");
		response.setStatus(2);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/forgetpassword",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> forgetPassword(@RequestParam(value="email") String email){
		fundooService.forgetPassword(email);
		response.setMessage("link sent to email,pls check and verify");
			response.setStatus(1);
			return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/resetpassword",method=RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> resetPassword(@RequestParam(value="token") String token,@RequestBody PasswordDTO passwordDto) throws Exception{
			fundooService.passwordReset(token,passwordDto);
			response.setMessage("Congratulations,your password is successfully changed");
			response.setStatus(4);
			return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
