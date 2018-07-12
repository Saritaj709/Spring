package com.bridgelabz.fundoo.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.exception.LoginException;
import com.bridgelabz.fundoo.exception.RegistrationException;
import com.bridgelabz.fundoo.model.LoginDTO;
import com.bridgelabz.fundoo.model.RegistrationDTO;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.service.FundooService;
import com.bridgelabz.fundoo.service.UserRepository;
import com.bridgelabz.fundoo.utility.FundooUtility;

@Service
public class FundooServiceImpl implements FundooService {

	@Autowired
	UserRepository userRepository;

	/*@Autowired
	User user;*/
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public void saveUser(RegistrationDTO dto) throws RegistrationException {
		// TODO Auto-generated method stub
		User user = new User();
		System.out.println("user " + user);
		int statusCode = FundooUtility.validateUser(dto);
		// Optional<User> checkUser=userRepository.findById(dto.getEmail());
		if (statusCode == 1) {
			// if(!checkUser.isPresent()) {
		// userRepository.save(dto);
			user.setId(dto.getId());
			user.setEmail(dto.getEmail());
			user.setFirstname(dto.getFirstname());
			user.setLastname(dto.getLastname());
			user.setPhoneNo(dto.getPhoneNo());
			user.setPassword(dto.getPassword());
			user.setConfirmPassword(dto.getConfirmPassword());
			userRepository.insert(user);
			System.out.println("user registered successfully");
		}
		else {
			System.out.println("unable to register");
		}
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
	public int loginUser(LoginDTO loginDto) throws LoginException {
		// TODO Auto-generated method stub
		Optional<User> checkUser = userRepository.findByEmail(loginDto.getEmail());
		if (!checkUser.isPresent()) {
			throw new LoginException("Email id not found");
		}
		User user1 = checkUser.get();
		int statusCode = FundooUtility.validateLogin(user1.getPassword(), user1.getConfirmPassword());
		/*
		 * if(checkUser.isPresent()) {
		 * if(checkUser.get().getPassword().equals(password)) {
		 * System.out.println("User exists");
		 */
		// if(statusCode==1)
		// return true;
		// }
		// }
		return statusCode;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> checkUser = userRepository.findById(user.getEmail());
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
		Optional<User> checkUser = userRepository.findById(email);
		if (checkUser.isPresent()) {
			userRepository.deleteById(email);
			System.out.println("user with email " + email + "removed");
			return true;
		}
		return false;
	}

}
