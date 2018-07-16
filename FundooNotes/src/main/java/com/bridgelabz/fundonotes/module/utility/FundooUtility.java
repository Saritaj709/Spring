package com.bridgelabz.fundonotes.module.utility;

import com.bridgelabz.fundonotes.module.exception.LoginException;
import com.bridgelabz.fundonotes.module.exception.RegistrationException;
import com.bridgelabz.fundonotes.module.model.LoginDTO;
import com.bridgelabz.fundonotes.module.model.PasswordDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;

public class FundooUtility {

	//private final static String CONTACT="^\\\\+([0-9\\\\-]?){9,11}[0-9]$";
	private final static String EMAIL="^\\w+@\\w+\\..{2,3}(.{2,3})?$";
	//private final static String PASSWORD="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{4,8}$";
	
	public  static int validateUser(RegistrationDTO user) throws RegistrationException{
		 if(!user.getEmail().matches(EMAIL)) {
			throw new RegistrationException("User email is not valid ,follow abc@gmail.com,abc.100@yahoo.com");
		}
		 else if(user.getFirstname().length()<3) {
			throw new RegistrationException("User Firstname should have atleast 3 characters");
		}
			else if(user.getLastname().length()<3) {
				throw new RegistrationException("User Lastname should have atleast 3 characters ");
			}
			
			else if(user.getPhoneNo().length()!=10) {
				throw new RegistrationException("contact no. is invalid");
			}
			else if(user.getPassword().length()<4) {
				throw new RegistrationException("Password is invalid,should have atleast 4 characters");
		  }
			else if(!user.getConfirmPassword().equals(user.getPassword())) {
				throw new RegistrationException("Password is invalid ,both password should be same ");
			}
				System.out.println("registration successful");
				return 1;
	}

	public static int validateLogin(LoginDTO loginDto) throws LoginException{
		if(!loginDto.getEmail().matches(EMAIL)) {
			throw new LoginException("Invalid email format");
		}
		return 1;
	}
	public static int validateReset(PasswordDTO passwordDto) throws RegistrationException {
		if(!passwordDto.getPassword().equals(passwordDto.getConfirmPassword())) {
			throw new RegistrationException("Passwords should be same");
		}
		return 1;
	}
}
