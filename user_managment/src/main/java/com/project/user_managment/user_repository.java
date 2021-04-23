package com.project.user_managment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;  
import java.sql.*;

public class user_repository {
	public Connection getConnection() {
		Connection con = null;
		String url = "Jdbc:mysql://localhost:3306/user_m";
		String username = "root";
		String password = "";


		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			
		}catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("success");
		return con;
	}
	


List<user> users;

public user_repository() {
	
	
	String url = "Jdbc:mysql://localhost:3306/user_m";
	String username = "root";
	String password = "";
	Connection con = getconnection();
	

	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url,username,password);
		
	}catch (Exception e) {
		System.out.println(e);
	}
	
	users = new ArrayList<>();
	
	user u1 = new user();
	
	u1.setId(0); ;
	u1.setFname("sumudu");
	u1.setLname("maduranga");
	u1.setEmail("smaduranga@gmail.com");
	u1.setAddress("no'10,shantha place,kurunegala");
	u1.setPhone_n(123456789);
	u1.setUsername("sanuwana");
	u1.setPassword("abcd123");
	users.add(u1);
}

public List<user> getAllUsers(){
	
	return users;
}

public user createuser(user u1) {
	
	String insertSql = "INSERT INTO `users`(`Id`, `Fname`,`Lname`,`Email`,`Address`, `Phone_n`, `username`,`password`) VALUES (?,?,?,?,?,?,?)";
	Connection con = getConnection();
	try {
		PreparedStatement st = con.prepareStatement(insertSql);
		st.setInt(1, u1.id);
		st.setString(2, u1.Fname);
		st.setString(3, u1.Lname);
		st.setString(4, u1.Email);
		st.setString(5, u1.Address);
		st.setInt(6, u1.Phone_n);
		st.setString(7, u1.username);
		st.setString(8, u1.password);
		
		st.executeUpdate();
	}catch (Exception e) {
		System.out.println(e);
	}
	users.add(u1);
	System.out.println(users);
	return (u1);
}

public user getuserid(int id) {
	String getsql = "SELECT * FROM `users` WHERE `Id` = '"+id+"' ";
	user ur = new user();
	Connection con = getConnection();
	
	try {
		Statement st = con.createStatement();
		ResultSet u1 = st.executeQuery(getsql);
		
		while(u1.next()) {
			
			ur.setId(u1.getInt(1));
			ur.setFname(u1.getString(2));
			ur.setLname(u1.getString(3));
			ur.setEmail(u1.getString(4));
			ur.setAddress(u1.getString(5));
			ur.setPhone_n(u1.getInt(6));
			ur.setUsername(u1.getString(7));
			ur.setPassword(u1.getString(8));
			
		}
		
		//con.close();
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ur;
}


public String deleteuser(int id) {
	String output = "";
	try {
		Connection con = getConnection();
		
		String deleteuser = "DELETE FROM `users` WHERE `Id` = '"+id+"'";
		PreparedStatement ps = con.prepareStatement(deleteuser);
		ps.execute();
		
		output = "Delete Successful";
		con.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return output;
}
	
public String updateUser(user user) {
	String output = "";
	
	try {
		Connection con = getConnection();
		
		String updateuser = "UPDATE `users` SET `Id`='"+user.getId()+"',Fname='"+user.getFname()+"',Lname='"+user.getLname()+"',Email='"+user.getEmail()+"',Address='"+user.getAddress()+"',Phone_n='"+user.getPhone_n()+"',username='"+user.getUsername()+"',password='"+user.getPassword()+"' WHERE id='"+user.getId()+"'";
		PreparedStatement st = con.prepareStatement(updateuser);

		st.executeUpdate();
		output = "Updated Successful";
		con.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return output;

}
private Connection getconnection() {
	// TODO Auto-generated method stub
	return null;
}
	
}