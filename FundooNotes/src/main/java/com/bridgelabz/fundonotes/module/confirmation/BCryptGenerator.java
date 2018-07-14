package com.bridgelabz.fundonotes.module.confirmation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.fundonotes.module.model.RegistrationDTO;


public class BCryptGenerator {
public String Bcrypt(RegistrationDTO registrationDto) {
	String password=registrationDto.getPassword();
	
	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	String hashedPassword=passwordEncoder.encode(password);
    return hashedPassword;
}
}
