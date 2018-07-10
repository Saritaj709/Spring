package com.bridgelabz.login.serviceimplementation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.login.service.UserRepository;
import com.bridgelabz.login.service.UserService;
import com.bridgelabz.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean registerUser(User user) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user2=userRepository.findById(user.getEmail());
		boolean status=false;
		if(user2.isPresent()==false) {
			userRepository.save(user);
			System.out.println("user saved");
			status= true;
			return status;
		}
		return status;
	}

	@Override
	public boolean getUserByEmail(String email) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user1=userRepository.findById(email);
		if(user1.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean loginUser(String email, String password) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user1=userRepository.findById(email);
		System.out.println("checking for login");
		if(user1.isPresent()) {
			System.out.println("is present");
		if(user1.get().getPassword().equals(password)) {
			System.out.println("User exists");
			return true;
			}
		}
		System.out.println("not logged");
			return false;
	}

	@Override
	public List<User> getUserDetails() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		List<User> userList=userRepository.findAll();
		return userList;
	}

}
