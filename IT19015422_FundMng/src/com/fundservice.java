package com;
import model.fund;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;

//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/fund")
public class fundservice {
	fund fundObj = new fund();
	@GET
	@Path("/fund")
	@Produces(MediaType.TEXT_HTML)
	public String readfunds()
	{
		 return fundObj.readfund();
		 }
	
	
	//INSERT DATA - POST METHOD
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertfund(@FormParam("id") String id,
	@FormParam("name") String name,
	@FormParam("amount") String amount,
	@FormParam("location") String location)
	{
	String output = fundObj.insertfund(id, name,amount, location);
	return output;
	}
	
	
	//read
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readfund()
		 {
		 return fundObj.readfund();
		 }

	
	//UPDATE - GET METHOD
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatefund(String fundData)
		{
		
	//Convert the input string to a JSON object
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	 
	 
	//Read the values from the JSON object
	 String id = fundObject.get("id").getAsString();
	 String name = fundObject.get("name").getAsString();
	 String amount = fundObject.get("amount").getAsString();
	 String location = fundObject.get("location").getAsString();
	 String output = fundObj.updatefund(id, name, amount,location);
	 return output;
	}
	
	
	//DELETE DATA
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletefund(String fundData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

	//Read the value from the element <id>
	 String id = doc.select("id").text();
	 String output = fundObj.deletefund(id);
	return output;
	}
	
}