package com.ibm.scientists.controller;

import java.util.List;

import com.google.gson.Gson;

import com.ibm.scientists.model.AddScientistRequest;
import com.ibm.scientists.model.Scientist;

public class JsonUtils {

	public static String getJson(Scientist scientist)
	{
		Gson gson = new Gson();
		String json = gson.toJson(scientist);
		return json;
	}
	
	public static String getFollowup(Scientist scientist)
	{
		Gson gson = new Gson();
		String json = gson.toJson(scientist.getFollowUp());
		return json;
	}
	
	public static String getListPersonJson(List<Scientist> people)
	{
		Gson gson = new Gson();
		String json = gson.toJson(people);
		return json;
	}
	
	public static Scientist getPersonFromJson(String json)
	{
		Gson gson = new Gson();
		Scientist scientist = gson.fromJson(json, Scientist.class);
		return scientist;
	}
	
	public static AddScientistRequest getAPRFromJson(String json)
	{
		Gson gson = new Gson();
		AddScientistRequest p = gson.fromJson(json, AddScientistRequest.class);
		return p;
	}

}
