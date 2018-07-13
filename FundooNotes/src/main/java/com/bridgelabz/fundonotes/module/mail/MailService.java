package com.bridgelabz.fundonotes.module.mail;

import com.bridgelabz.fundonotes.module.model.RegistrationDTO;

public interface MailService {
	public boolean activationMail(String email, RegistrationDTO dto);

	public boolean passwordResetMail(String email);
}
