package com.bridgelabz.login.userservice;

import java.sql.Connection;
import java.sql.SQLException;

import com.bridgelabz.login.model.User;

public interface Service {
public Connection getConnection() throws ClassNotFoundException;
public boolean registerUser(User user) throws ClassNotFoundException;
public String getUserByEmail(String email) throws SQLException, ClassNotFoundException;
public User loginUser(String email,String password) throws SQLException, ClassNotFoundException;

}
