package com.bridgelabz.fundonotes.module.service;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.module.confirmation.JwtToken;
import com.bridgelabz.fundonotes.module.exception.LoginException;
import com.bridgelabz.fundonotes.module.exception.RegistrationException;
import com.bridgelabz.fundonotes.module.model.LoginDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.model.User;
import com.bridgelabz.fundonotes.module.utility.FundooUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class FundooServiceImpl implements FundooService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public String saveUser(RegistrationDTO dto) throws RegistrationException {
		// TODO Auto-generated method stub
		User user = new User();
		System.out.println("user " + user);
		int statusCode = FundooUtility.validateUser(dto);
		Optional<User> checkUser=userRepository.findByEmail(dto.getEmail());
		if (statusCode == 1) {
			if(!checkUser.isPresent()) {
			user.setEmail(dto.getEmail());
			user.setFirstname(dto.getFirstname());
			user.setLastname(dto.getLastname());
			user.setPhoneNo(dto.getPhoneNo());
			user.setPassword(dto.getPassword());
			userRepository.insert(user);
			JwtToken jwt=new JwtToken();
			String jwtToken = jwt.tokenGenerator(dto);
			return jwtToken;
			}
		else {
			throw new RegistrationException("email id already exists,unable to register");
			}
		}
		return null;
	}
	@Override
	public boolean getUserByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<User> checkUser = userRepository.findByEmail(email);
		if (checkUser.isPresent()) {
			System.out.println(email + " is available ");
			return true;
		}
		return false;
	}

	@Override
	public void loginUser(LoginDTO loginDto) throws LoginException {
		// TODO Auto-generated method stub
		int statusCode = FundooUtility.validateLogin(loginDto);
		Optional<User> checkUser=userRepository.findByEmail(loginDto.getEmail());
		if(statusCode==1) {
		if(checkUser.isPresent()) {
			if(checkUser.get().getPassword().equals(loginDto.getPassword())) {
				System.out.println("Login Successful");
			}else {
				throw new LoginException("Password does invalid");
			}
		}
		else {
			throw new LoginException("Email does not exist");
			}
		}
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser.isPresent()) {
			userRepository.save(user);
			System.out.println("User details successfully updated");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(String email) {
		// TODO Auto-generated method stub
		Optional<User> checkUser = userRepository.findByEmail(email);
		if (checkUser.isPresent()) {
			userRepository.deleteById(email);
			System.out.println("user with email " + email + "removed");
			return true;
		}
		return false;
	}

	@Override
	public boolean activateJwt(String token) {
		// TODO Auto-generated method stub
		Claims claims=Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("Sarita")).parseClaimsJws(token).getBody();
		Optional<User> user=userRepository.findById(claims.getSubject());
		user.get().setActivate("true");
		userRepository.save(user.get());
		System.out.println("User account activated ");
		return true;
	}

	@Override
	public boolean sendActivationMail(String token, RegistrationDTO registrationDto) {
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
	public boolean forgetPassword(String email) {
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
