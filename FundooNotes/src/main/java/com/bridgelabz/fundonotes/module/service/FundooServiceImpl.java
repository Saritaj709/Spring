package com.bridgelabz.fundonotes.module.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.module.exception.LoginException;
import com.bridgelabz.fundonotes.module.exception.RegistrationException;
import com.bridgelabz.fundonotes.module.model.LoginDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.model.User;
import com.bridgelabz.fundonotes.module.utility.FundooUtility;

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
	public void saveUser(RegistrationDTO dto) throws RegistrationException {
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
			}
		else {
			throw new RegistrationException("email id already exists,unable to register");
			}
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

}
