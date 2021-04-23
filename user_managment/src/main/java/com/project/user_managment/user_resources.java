package com.project.user_managment;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/users")


public class user_resources {

user_repository ur = new user_repository();
	
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	public List<user> getAllUsers() {
		return ur.getAllUsers();
    }
	
	
	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	public user createuser(user u1) {
		return ur.createuser(u1);
	}
	
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("id") String id) {
	user res = new user();
	res = ur.getuserid(Integer.parseInt(id));
	Gson test = new Gson();
	String jsonObject = test.toJson(res);
	return jsonObject;
	}
	
	@DELETE
	@Path("/deleteuser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteuser(@PathParam("id") int id) {
		return ur.deleteuser(id);
	}
	
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateUser(user u1) 
	{ 
		return ur.updateUser(u1);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<user> getAlluser(){
		return ur.getAllUsers();
	}
	
}
