package com.bridgelabz.fundonotes.module.service;

import java.util.List;

import com.bridgelabz.fundonotes.module.exception.RegistrationException;
import com.bridgelabz.fundonotes.module.model.PasswordDTO;
import com.bridgelabz.fundonotes.module.model.LoginDTO;
import com.bridgelabz.fundonotes.module.model.RegistrationDTO;
import com.bridgelabz.fundonotes.module.model.User;

public interface FundooService {
	public List<User> getAllUsers();

	public String saveUser(RegistrationDTO user) throws RegistrationException;

	public boolean getUserByEmail(String email);

	public void loginUser(LoginDTO loginDto);

	public String updateUser(User user);

	public boolean deleteUser(String email);

	public boolean activateJwt(String token);

	public void forgetPassword(String email);
	
	public void passwordReset(String token,PasswordDTO dto) throws RegistrationException, Exception;

}
