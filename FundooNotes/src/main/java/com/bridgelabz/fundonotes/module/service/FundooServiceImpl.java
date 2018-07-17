package com.bridgelabz.fundonotes.module.service;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.module.confirmation.JwtToken;
import com.bridgelabz.fundonotes.module.exception.LoginException;
import com.bridgelabz.fundonotes.module.exception.RegistrationException;
import com.bridgelabz.fundonotes.module.model.PasswordDTO;
import com.bridgelabz.fundonotes.module.model.LoginDTO;
import com.bridgelabz.fundonotes.module.model.MailDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.model.User;
import com.bridgelabz.fundonotes.module.utility.FundooUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class FundooServiceImpl implements FundooService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtToken jwtToken = new JwtToken();

	@Autowired
	private JmsProducer producer;

	@Override
	public List<User> getAllUsers() {

		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public String saveUser(RegistrationDTO dto) throws RegistrationException {

		User user = new User();
		System.out.println("user " + user);
		int statusCode = FundooUtility.validateUser(dto);
		Optional<User> checkUser = userRepository.findByEmail(dto.getEmail());
		if (statusCode == 1) {
			if (!checkUser.isPresent()) {
				user.setEmail(dto.getEmail());
				user.setFirstname(dto.getFirstname());
				user.setLastname(dto.getLastname());
				user.setPhoneNo(dto.getPhoneNo());
				user.setPassword(passwordEncoder.encode(dto.getPassword()));
				userRepository.insert(user);
				JwtToken jwt = new JwtToken();
				String jwtToken = jwt.tokenGenerator(dto);
				MailDTO mail = new MailDTO();
				mail.setTo(dto.getEmail());
				mail.setSubject("Account activation mail");
				mail.setText("Click here to verify your account:\n\n"
						+ "http://192.168.0.73:8080/Fundoonotes/activateaccount/?" + jwtToken);
				producer.sender(mail);
				return jwtToken;
			} else {
				throw new RegistrationException("email id already exists,unable to register");
			}
		}
		return null;
	}

	@Override
	public boolean getUserByEmail(String email) {

		Optional<User> checkUser = userRepository.findByEmail(email);
		if (checkUser.isPresent()) {
			System.out.println(email + " is available ");
			return true;
		}
		return false;
	}

	@Override
	public void loginUser(LoginDTO loginDto) throws LoginException {

		FundooUtility.validateLogin(loginDto);
		Optional<User> checkUser = userRepository.findByEmail(loginDto.getEmail());
		if (!checkUser.isPresent())
			throw new LoginException("This Email id does not exist");
		else if (!checkUser.get().isActivate()) {
			throw new LoginException("User account is not activated yet");
		} else {
			if (!passwordEncoder.matches(loginDto.getPassword(), checkUser.get().getPassword())) {
				throw new LoginException("Password unmatched");
			}
		}
	}

	@Override
	public String updateUser(User user) {

		Optional<User> checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser.isPresent()) {
			userRepository.save(user);
			JwtToken jwt = new JwtToken();
			String jwtToken = jwt.tokenGenerator(user);
			System.out.println("User details successfully updated");
			return jwtToken;
		}
		return null;
	}

	@Override
	public boolean deleteUser(String email) {

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

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("Sarita")).parseClaimsJws(token)
				.getBody();
		Optional<User> user = userRepository.findById(claims.getSubject());
		user.get().setActivate(true);
		userRepository.save(user.get());
		System.out.println("User account activated ");
		return true;
	}

	@Override
	public void forgetPassword(String email) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByEmail(email);
		System.out.println("user found");
		if (!user.isPresent()) {
			throw new LoginException("User is not present");
		}
		String generatedToken = jwtToken.tokenGenerator(user.get());
		System.out.println(generatedToken);
		MailDTO mail = new MailDTO();
		mail.setTo(email);
		mail.setSubject("Password reset mail");
		mail.setText("http://localhost:8080/Fundoonotes/resetpassword/?token=" + generatedToken);
		producer.sender(mail);
	}

	@Override
	public void passwordReset(String token, PasswordDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("Sarita")).parseClaimsJws(token)
				.getBody();
		System.out.println("Subject : " + claims.getSubject());
		Optional<User> user = userRepository.findById(claims.getSubject());
		FundooUtility.validateReset(dto);
		if (!user.isPresent()) {
			throw new Exception("User not found");
		}
		user.get().setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepository.save(user.get());
		System.out.println("Password changed");
	}
}
