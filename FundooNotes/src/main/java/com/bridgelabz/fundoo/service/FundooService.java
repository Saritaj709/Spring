package com.bridgelabz.fundoo.service;

import java.util.List;

import com.bridgelabz.fundoo.exception.RegistrationException;
import com.bridgelabz.fundoo.model.LoginDTO;
import com.bridgelabz.fundoo.model.RegistrationDTO;
import com.bridgelabz.fundoo.model.User;

public interface FundooService {
	public List<User> getAllUsers();

	public void saveUser(RegistrationDTO user) throws RegistrationException;

	public boolean getUserByEmail(String email);

	public int loginUser(LoginDTO loginDto);

	public boolean updateUser(User user);

	public boolean deleteUser(String email);
}
