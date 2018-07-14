package com.bridgelabz.fundonotes.module.confirmation;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptGenerator {
	public String Bcrypt(String password) {
		// String password=registrationDto.getPassword();
		/*
		 * BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(); String
		 * hashedPassword=passwordEncoder.encode(password); return hashedPassword;
		 */
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean passwordChecking(String plainPass, String hashedPass) {
		if (BCrypt.checkpw(plainPass, hashedPass))
			return true;
		else
			return false;
	}
}
