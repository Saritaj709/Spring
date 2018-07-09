package com.bridgelabz.login.serviceimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.bridgelabz.login.model.User;
import com.bridgelabz.login.userservice.Service;

@Repository
public class UserRepository implements Service{
	Connection con = null;

	@Override
	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("connecting to database");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC?user=root&password=root");

			System.out.println("connection established");
		} 
		catch (SQLException e) {
	           e.printStackTrace();
	       
	       }
		return con;
	}

	@Override
	public boolean registerUser(User user) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement pst = null;
		boolean status=false;
		String query1 = "insert into login_register values(?,?,?,?,?)";
		try {
			con = getConnection();
			pst = con.prepareStatement(query1);

			pst.setString(1, user.getFirstname());
			pst.setString(2, user.getLastname());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getMobileNo());
			pst.setString(5, user.getPassword());

			pst.executeUpdate();
			System.out.println("details received");
		    status=true;
       
       }
		catch (SQLException e) {
	           e.printStackTrace();
	       
	       }
	       finally {
	           if(con!=null)
	           {
	               try {
	                   con.close();
	               } catch (SQLException e) {
	                   // TODO Auto-generated catch block
	                   e.printStackTrace();
	               }
	           }
	           if(pst!=null)
	           {
	               try {
	                   pst.close();
	               } catch (SQLException e) {
	                   // TODO Auto-generated catch block
	                   e.printStackTrace();
	               }
	           }
	       }
		return status;
	}

	@Override
	public String getUserByEmail(String email) throws SQLException, ClassNotFoundException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		User user = new User();
		String query = "select * from login_register where email=?";
		con = getConnection();
		pst = con.prepareStatement(query);
		pst.setString(1, email);
		rs = pst.executeQuery(query);
		if (rs.next()) {
			user.setEmail(rs.getString(3));
			System.out.println("The user email exists");
		}
		return email;
	}

	@Override
	public User loginUser(String email, String password) throws SQLException, ClassNotFoundException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		User user = new User();

		String query = "select * from login_register where email=? and password=?";
		con = getConnection();
		pst = con.prepareStatement(query);
		pst.setString(1, email);
		pst.setString(2, password);
		rs = pst.executeQuery();

		if (rs.next()) {
			System.out.println("user exists");
			user.setFirstname(rs.getString(1));
			user.setLastname(rs.getString(2));
			user.setEmail(rs.getString(3));
			user.setMobileNo(rs.getString(4));
			user.setPassword(rs.getString(5));

			System.out.println("user validated");

		}
		return user;
	}
}
