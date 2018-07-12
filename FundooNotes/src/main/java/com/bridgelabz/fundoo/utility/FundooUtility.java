package com.bridgelabz.fundoo.utility;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoo.exception.LoginException;
import com.bridgelabz.fundoo.exception.RegistrationException;
import com.bridgelabz.fundoo.model.RegistrationDTO;
import com.bridgelabz.fundoo.service.UserRepository;

public class FundooUtility {

	//private final String CONTACT="^\\\\+([0-9\\\\-]?){9,11}[0-9]$";
	private final static String EMAIL="^\\w+@\\w+\\..{2,3}(.{2,3})?$";
	//private final String PASSWORD="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{4,8}$";
	
	@Autowired
	UserRepository userRepository;
	
	public static int validateUser(RegistrationDTO user) throws RegistrationException{
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
				throw new RegistrationException("Password is invalid Follow aD$1,1heD$hi  ,atleast one uppercae,one lowercase,one special char,4 to 8 characters in length");
		  }
			else if(user.getConfirmPassword().length()<4) {
				throw new RegistrationException("Password is invalid Follow aD$1,1heD$hi  ,atleast one uppercae,one lowercase,one special char,4 to 8 characters in length");
			}
				System.out.println("registration successful");
				return 1;
	}

	public static int validateLogin(String password1,String password2) throws LoginException{
		if(!password1.equals(password2)) {
			throw new LoginException("Unmatched passwords");
		}
		return 1;
	}
/* public String matchEmail(String email) {
	 Pattern pattern=Pattern.compile(EMAIL);
	 Matcher matcher=pattern.matcher(email);
	 if(matcher.matches()) {
		 System.out.println("valid email");
	 }
	 else {
		 throw new LoginException(email);
	 }
	 return email;
 }
 public String matchNumber(String contact) throws RegistrationException {
	 Pattern pattern=Pattern.compile(CONTACT);
	 Matcher matcher=pattern.matcher(contact);
	 if(matcher.matches()) {
		 System.out.println("valid contact no.");
	 }
	 else {
		 throw new RegistrationException(contact);
	 }
	 return contact;
 }*/
}
