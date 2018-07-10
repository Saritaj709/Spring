package com.bridgelabz.login.service;

import java.sql.SQLException;
import java.util.List;

import com.bridgelabz.model.User;

public interface UserService {
public boolean registerUser(User user) throws ClassNotFoundException;
public boolean getUserByEmail(String email) throws SQLException, ClassNotFoundException;
public boolean loginUser(String email,String password) throws SQLException, ClassNotFoundException;
public List<User> getUserDetails() throws ClassNotFoundException;

}
