package com.bridgelabz.fundoo.service;

import java.util.List;

import com.bridgelabz.fundoo.model.User;

public interface FundooService {
	public List<User> getAllUsers();

	public boolean saveUser(User user);

	public boolean getUserByEmail(String email);

	public boolean loginUser(String email, String password);

	public boolean updateUser(User user);

	public boolean deleteUser(String email);
}
