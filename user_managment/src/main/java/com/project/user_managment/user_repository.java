package com.project.user_managment;

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

	Connection con = getConnection();

	
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
	
	String insertSql = "INSERT INTO `users`(`Id`, `Fname`, `Lname`, `Email`, `Address`, `Phone_n`, `username`, `password`) VALUES (?,?,?,?,?,?,?,?)";
	Connection con = getConnection();
	try {
		PreparedStatement st = con.prepareStatement(insertSql);
		st.setInt(1, u1.Id);
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
	return u1;
}

public user getuserid(int Id) {
	String getsql = "SELECT * FROM `users` WHERE `Id` = '"+Id+"' ";
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


public String readAddusers() {
	String output = "";
	
	try {
		//connect to DB
		Connection con = getConnection();
		
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		output = "<table border='1'><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Address</th><th>Contact No</th><th>Username</th><th>Password</th>" ;	
		String query = "SELECT * FROM `users`";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		//iterate through the rows in the result set
		while (rs.next())
		{
			String Id = Integer.toString(rs.getInt ("Id"));
			String Fname = rs.getString("Fname");
			String Lname = rs.getString("Lname");
			String Email = rs.getString("Email");
			String Address = rs.getString("Address");
			String Phone_n = rs.getString("Phone_n");
			String username = rs.getString("username");
			String password = rs.getString("password");
			
			//Add into the html table
			
			output +="<tr><td>" + Id + "</td>";
			output +="<td>" + Fname + "</td>";
			output +="<td>" + Lname + "</td>";
			output +="<td>" + Email + "</td>";
			output +="<td>" + Address + "</td>";
			output +="<td>" + Phone_n + "</td>";
			output +="<td>" + username + "</td>";
			output +="<td>" + password + "</td>";
			
		}
		//close th db connection
		con.close();
		
		//complete the html table
			output += "</table>";
	}
	catch (Exception e) {
		output = "Error while reading the users.";
		System.err.println(e.getMessage());
	}
	return output;
}


}