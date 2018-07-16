package com.bridgelabz.fundonotes.module.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.module.model.MailDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.service.UserRepository;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean activationMail(String token, RegistrationDTO registrationDto) {
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		try {
			helper.setTo(registrationDto.getEmail());
		
		helper.setSubject( "Email verification mail");
		helper.setText("Click here to verify your account:\n\n"+"http://192.168.0.73:8080/Fundoonotes/activateaccount/?"+token);
	
		mailSender.send(message);
		return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean sendMail(MailDTO mail) {
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		try {
			helper.setTo(mail.getTo());
		
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getText());
		mailSender.send(message);
		System.out.println("mail sent");
		return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean passwordResetMail(String email,String token) {
		// TODO Auto-generated method stub
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setSubject("Password reset mail");
			helper.setText("Click here to verify your account:\\n\\n\"+\"http://192.168.0.73:8080/Fundoonotes/resetpassword/?"+token);
			//helper.setText("Your new password is : "+userRepository.findById(email).get().getPassword());
			mailSender.send(message);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
