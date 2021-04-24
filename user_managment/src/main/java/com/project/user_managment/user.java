package com.project.user_managment;

import java.util.Arrays;

public class user {
	public int Id;
	public String Fname;
	public String Lname;
	public String Email;
	public String Address;
	public int Phone_n;
	public String username;
	public String password;
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	
	public int getPhone_n() {
		return Phone_n;
	}
	public void setPhone_n(int phone_n) {
		Phone_n = phone_n;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "user [Id=" + Id + ", Fname=" + Fname + ", Lname=" + Lname + ", Email=" + Email + ", Address=" + Address
				+ ", Phone_n=" + Phone_n + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
