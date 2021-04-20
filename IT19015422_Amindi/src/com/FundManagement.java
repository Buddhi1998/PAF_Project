package com;

import model.FundDB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/FundDB")
public class FundManagement {

	FundDB fundObject = new FundDB();

	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON) 
	public String readItems() 
	{ 
	 return fundObject.readItems(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(
	@FormParam("funderName") String funderName, 
	@FormParam("fundAmount") String fundAmount,
	@FormParam("fundDate") String fundDate,
	@FormParam("funderLocation") String funderLocation) 
	{ 
	 String output = fundObject.insertFund(funderName,fundAmount,fundDate,funderLocation); 
	 return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String fundData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(fundData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String funderID = itemObject.get("funderID").getAsString(); 
	 String funderName = itemObject.get("funderName").getAsString(); 
	 String fundAmount = itemObject.get("fundAmount").getAsString(); 
	 String fundDate = itemObject.get("fundDate").getAsString(); 
	 String funderLocation = itemObject.get("funderLocation").getAsString(); 
	 String output = fundObject.updateFund(funderID, funderName, fundAmount, fundDate, funderLocation); 
	 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String fundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String funderID = doc.select("funderID").text(); 
	 String output = fundObject.deleteFund(funderID); 
	return output; 
	}
	
	
	
}
