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
	
	public String readFunds() 
	{ 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>funderName</th><th>fundAmount</th>" +
					"<th>fundDate</th>" + 
					"<th>funderLocation</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
 
			String query = "select * from fund_management"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String funderID = Integer.toString(rs.getInt("funderID")); 
				String funderName = rs.getString("funderName"); 
				String fundAmount = Double.toString(rs.getDouble("fundAmount")); 
				String fundDate = rs.getString("fundDate"); 
				String funderLocation = rs.getString("funderLocation"); 
				
				// Add into the html table
				output += "<tr><td>" + funderID + "</td>"; 
				output += "<td>" + funderName + "</td>"; 
				output += "<td>" + fundAmount + "</td>"; 
				output += "<td>" + fundDate + "</td>"; 
				output += "<td>" + funderLocation + "</td>"; 
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='funds.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='funderID' type='hidden' value='" + funderID 
						+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			
			// Complete the html table
			output += "</table>"; 
			
			
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the fund details."; 
			System.err.println(e.getMessage()); 
			
		} 
		return output;
		 
	} 
	
}