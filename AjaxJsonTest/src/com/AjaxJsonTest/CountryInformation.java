package com.AjaxJsonTest;

import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CountryInformation extends HttpServlet {
	
	
	/**
	 * Generated code
	 */
	private static final long serialVersionUID = 1L;

	public CountryInformation()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String countryCode = request.getParameter("countryCode");
		
		// Setting up the response
		PrintWriter out = response.getWriter();
		response.setContentType("text/HTML");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
       	response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        
		Gson gson = new Gson();
		JsonObject myJsonObj = new JsonObject();
		
		Country countryInfo = getInfo(countryCode);
		
		// converting to Json
		JsonElement cObj = gson.toJsonTree(countryInfo);
		if(countryInfo.getName() == null)
		{
			myJsonObj.addProperty("success", false);
		}
		else
		{
			myJsonObj.addProperty("success", true);
		}
		myJsonObj.add("countryInfo", cObj);
		out.println(myJsonObj.toString());
		out.close();
	}
	
	// Getting country information from country code
	private Country getInfo(String countryCode)
	{
		Country country = new Country();
			
		// In this scenario, let us first define the country details manually instead of a DB
		if(countryCode.equals("usa"))
		{
			country.setCode("usa");
			country.setName("United States of America");
			country.setContinent("North America");
			country.setRegion("Northern Hemisphere");
			country.setLifeExpectancy(65.3);
			country.setGnp(99.9999);
		}
		
		return country;
	}
}
