package com.bridgelabz.login.userservice;

import java.sql.SQLException;
import java.util.List;

import com.bridgelabz.login.model.User;
import com.mongodb.DBCollection;

public interface Service {
public DBCollection getConnection() throws ClassNotFoundException;
public boolean registerUser(User user) throws ClassNotFoundException;
public boolean getUserByEmail(String email) throws SQLException, ClassNotFoundException;
public boolean loginUser(String email,String password) throws SQLException, ClassNotFoundException;
public List<User> getUserDetails() throws ClassNotFoundException;

}
