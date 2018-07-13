package com.bridgelabz.fundonotes.module.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.service.UserRepository;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean activationMail(String token, RegistrationDTO registrationDto) {
		// TODO Auto-generated method stub
		String from = "saritaj709@gmail.com";
		String pass = "mammasbeti";
		String to = registrationDto.getEmail(); // list of recipient email addresses
		String subject = "Email verification mail";
		String body = "Click here ro verify your account:\n\n"+"http://192.168.0.73/Fundoonotes/activateaccount/token=?"+token;
		Properties properties = System.getProperties();
		String host = "smtp.gmail.com";
		properties.put("mail.smtp.starttls.enable", "true");
		
		properties.put("mail.smtp.ssl.trust",host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", pass);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setText(body);

			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
			}
	}

	@Override
	public boolean passwordResetMail(String email) {
		// TODO Auto-generated method stub
		String from="saritaj709@gmail.com";
		String pass="mammasbeti";
		String to=email;
		String subject="Password recovery mail";
		String body="Your current password is :"+userRepository.findById(email).get().getPassword();
		Properties properties=System.getProperties();
		String host="smtp.gmail.com";
		properties.put("mail.smtp.starttls.enable","true");
		
		properties.put("mail.smtp.ssl.trust",host);
		properties.put("mail.smtp.user",from);
		properties.put("mail.smtp.password",pass);
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.auth","true");
		
		Session session=Session.getDefaultInstance(properties);
		MimeMessage message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setText(body);
			Transport transport=session.getTransport("smtp");
			transport.connect(host,from,pass);
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
