package com.bridgelabz.login.serviceimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bridgelabz.login.model.User;
import com.bridgelabz.login.userservice.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@Repository
public class UserRepository implements Service {
	Connection con = null;

	@Override
	public DBCollection getConnection() throws ClassNotFoundException {
		// Create connection
		System.out.println("Creating connection");
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Connected with mongodb");

		// Connecting with database.
		System.out.println("Connecting with datbase");
		DB db = mongoClient.getDB("spring");
		System.out.println("Connected with Db");
		System.out.println("Database name : " + db.getName());

		// Creating new collection.
		DBCollection coll = db.getCollection("login_register");
		System.out.println("Collection created");

		return coll;
	}

	@Override
	public boolean registerUser(User user) throws ClassNotFoundException {
		DBCollection coll = getConnection();
		boolean status=false;
		BasicDBObject basicObject=new BasicDBObject("firstname",user.getFirstname()).append("lastname",user.getLastname()).append("email",user.getEmail()).append("mobileNo",user.getMobileNo()).append("password",user.getPassword());
	coll.insert(basicObject);	
	System.out.println("Details inserted");
	status=true;
	return status;
	} 
	@Override
public List<User> getUserDetails() throws ClassNotFoundException{
	List<User> userList=new LinkedList<>();
	DBCollection coll=getConnection();
	Cursor cursor=coll.find();
	while(cursor.hasNext()) {
		User user=new User();
		BasicDBObject basicObj=(BasicDBObject)cursor.next();
		user.setFirstname(basicObj.getString("firstname").toString());
		user.setLastname(basicObj.getString("lastname").toString());
		user.setEmail(basicObj.getString("email").toString());
		user.setMobileNo(basicObj.getString("mobileNo").toString());
		user.setPassword(basicObj.getString("password").toString());
		userList.add(user);
	}
	return userList;
}
	@Override
	public boolean getUserByEmail(String email1) throws SQLException, ClassNotFoundException {
		DBCollection coll = getConnection();

		BasicDBObject object = new BasicDBObject("email", email1);
		Cursor cursor = coll.find(object);
		while (cursor.hasNext()) {
			BasicDBObject basicObj = (BasicDBObject) cursor.next();
			if (basicObj.get("email").toString().equals(email1)) {
				System.out.println("email already exists");
				return true;
			}
		}
		System.out.println("email does not exist");
		return false;
	}

	@Override
	public boolean loginUser(String email1, String password) throws SQLException, ClassNotFoundException {

		DBCollection coll = getConnection();
		System.out.println("Checking for login");
		BasicDBObject object = new BasicDBObject("email", email1);
		Cursor cursor = coll.find(object);
		while (cursor.hasNext()) {
			BasicDBObject basicObj = (BasicDBObject) cursor.next();
			if (basicObj.get("email").toString().equals(email1)
					&& basicObj.get("password").toString().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
