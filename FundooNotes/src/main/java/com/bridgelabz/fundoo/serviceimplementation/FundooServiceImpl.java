package com.bridgelabz.fundoo.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.service.FundooService;
import com.bridgelabz.fundoo.service.UserRepository;

@Service
public class FundooServiceImpl implements FundooService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> userList=userRepository.findAll();
		return userList;
	}

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		boolean status=false;
		Optional<User> checkUser=userRepository.findById(user.getEmail());
		if(checkUser.isPresent()==false) {
			userRepository.save(user);
			System.out.println("user registered successfully");
			status=true;
			return status;
		}
		return status;
	}

	@Override
	public boolean getUserByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<User> checkUser=userRepository.findById(email);
		if(checkUser.isPresent()) {
			System.out.println(email+" is available ");
			return true;
		}
		return false;
	}

	@Override
	public boolean loginUser(String email,String password) {
		// TODO Auto-generated method stub
		Optional<User> checkUser=userRepository.findById(email);
		if(checkUser.isPresent()) {
			if(checkUser.get().getPassword().equals(password)) {
				System.out.println("User exists");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> checkUser=userRepository.findById(user.getEmail());
		User user1=new User();
		if(checkUser.isPresent()) {
			userRepository.save(user);
			System.out.println("User details successfully updated");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(String email) {
		// TODO Auto-generated method stub
		Optional<User> checkUser=userRepository.findById(email);
		if(checkUser.isPresent()) {
			userRepository.deleteById(email);
			System.out.println("user with email "+email+"removed");
			return true;
		}
		return false;
	}

}
