package model;

import java.sql.*;

public class FundDB {

	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		
		return con; 
	} 
	
	public String insertFund(String funderName, String fundAmount, String fundDate,String funderLocation) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
			
			// create a prepared statement
			String query = " insert into fund_management (`funderID`,`funderName`,`fundAmount`,`fundDate`,`funderLocation`)" + " values (?, ?, ? ,? ,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, funderName); 
			preparedStmt.setDouble(3, Double.parseDouble(fundAmount)); 
			preparedStmt.setString(4, fundDate);
			preparedStmt.setString(5, funderLocation); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the funding details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
}