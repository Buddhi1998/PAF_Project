package model;
import java.sql.*;
public class product{
	
//A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName,username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }


//insert
	public String insertproduct(String id, String name, String amount, String des)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into product(`id`, `name`, `amount`, `des`)" + " values (?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	// preparedStmt.setInt(1, 0);
	 preparedStmt.setString(1, id);
	 preparedStmt.setString(2, name);
	 preparedStmt.setDouble(3, Double.parseDouble(amount));
	 preparedStmt.setString(4, des);
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the product.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }





//read
	public String readproduct()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>product id</th><th>product Name</th>" +"<th>product amount</th>" +"<th>product desription</th>" +"<th>Update</th><th>Remove</th></tr>";
	
	 String query = "select * from product";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	
	 String id = rs.getString("id");
	 String name = rs.getString("name");
	 String amount = Double.toString(rs.getDouble("amount"));
	 String des = rs.getString("des");
	 
	 // Add into the html table
	 output += "<tr><td>" + id + "</td>";
	 output += "<td>" + name + "</td>";
	 output += "<td>" + amount + "</td>";
	 output += "<td>" + des + "</td>";
	 // buttons
	 output += "<td> <input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='product.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }



//update
	public String updateproduct(String id, String name, String amount, String des)
	{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 
	 
	 // create a prepared statement update
	 String query = "UPDATE product SET name=?,amount=?,des=? WHERE id=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, id);
	 preparedStmt.setString(2, name);
	 preparedStmt.setDouble(3, Double.parseDouble(amount));
	 preparedStmt.setString(4, des);

	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the product.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }


//delete
	public String deleteproduct(String id)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 
	 
	 // create a prepared statement for delete 
	 String query = "delete from product where id=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the product.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
}