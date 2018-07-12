package com.bridgelabz.fundoo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.fundoo.controller.FundooController;
import com.bridgelabz.fundoo.model.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final Logger logger = LoggerFactory.getLogger(FundooController.class);
	
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<ResponseDTO> register(RegistrationException e){
		logger.error("Registration exception");
		ResponseDTO response=new ResponseDTO();
		response.setMessage("Registration error");
		response.setStatus(1);
		System.out.println(e);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ResponseDTO> login(LoginException e){
		logger.error("Login exception");
		ResponseDTO response=new ResponseDTO();
		response.setMessage("Login error");
		response.setStatus(2);
		System.out.println(e);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	/*@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDTO> controller(Exception e) {
		logger.error("other exceptions");
		ResponseDTO response=new ResponseDTO();
		response.setMessage("Some exceptions occured");
		response.setStatus(-1);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}*/
}
