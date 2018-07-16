package com.bridgelabz.fundonotes.module.mail;

import com.bridgelabz.fundonotes.module.model.MailDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;

public interface MailService {
	public boolean activationMail(String email, RegistrationDTO dto);

	public boolean passwordResetMail(String email,String token);

	//public boolean forgetPassword(String email);

	public boolean sendMail(MailDTO mail);
	
}
