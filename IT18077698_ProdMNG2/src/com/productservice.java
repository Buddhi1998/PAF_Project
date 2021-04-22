package com;
import model.product;

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
@Path("/product")
public class productservice {
	product productObj = new product();
	@GET
	@Path("/product")
	@Produces(MediaType.TEXT_HTML)
	public String readproducts()
	{
		 return productObj.readproduct();
		 }
	
	
	//INSERT DATA - POST METHOD
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertproduct(@FormParam("id") String id,
	@FormParam("name") String name,
	@FormParam("amount") String amount,
	@FormParam("des") String des)
	{
	String output = productObj.insertproduct(id, name,amount, des);
	return output;
	}
	
	
	//read
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readproduct()
		 {
		 return productObj.readproduct();
		 }

	
	//UPDATE - GET METHOD
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateproduct(String productData)
		{
		
	//Convert the input string to a JSON object
	 JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject();
	 
	 
	//Read the values from the JSON object
	 String id = productObject.get("id").getAsString();
	 String name = productObject.get("name").getAsString();
	 String amount = productObject.get("amount").getAsString();
	 String des = productObject.get("des").getAsString();
	 String output = productObj.updateproduct(id, name, amount,des);
	 return output;
	}
	
	
	//DELETE DATAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteproduct(String productData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

	//Read the value from the element <id>
	 String id = doc.select("id").text();
	 String output = productObj.deleteproduct(id);
	return output;
	}
	
}