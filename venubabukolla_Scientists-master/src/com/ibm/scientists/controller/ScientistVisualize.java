package com.ibm.scientists.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ibm.profile.scientists.ProfileIInsightsUserModelling;
import com.ibm.scientists.Config;
import com.ibm.scientists.model.Scientist;

/*import com.ibm.personafusion.Config;
import com.ibm.personafusion.model.Person;
import com.ibm.personafusion.service.WatsonUserModeller;*/

/** Handles the /api/viz API endpoint.
 *  Visualizes a person's personality traits.
 *  Requires 'fname' and 'lname' query parameters to search.
 *  Returns an HTML string of the visualization.
 *  @author Sean Welleck **/
@Path("/viz")
public class ScientistVisualize 
{
	
	/** Returns people results as a JSON string. **/
	@GET
	public Response handleViz(@Context UriInfo ui)
	{
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		String fname = SearchController.getParam("fname", queryParams);
		String lname = SearchController.getParam("lname", queryParams);
		
		if (fname == null || lname == null)
		{
			return error();
		}
		
		String fullName = fname + " " + lname;
		System.out.println("fname=" + fname + " lname=" + lname);
		
		//Person p = Config.cc.getPerson(fullName.toUpperCase());
		Scientist s = Config.cc.getScientist(fullName.toUpperCase());
				
		if (s == null)
		{
			return error();
		}
		
		ProfileIInsightsUserModelling pfiUserModelling = new ProfileIInsightsUserModelling();
		String vizHTML = pfiUserModelling.getPersonVizHTML(s);
		
		if (vizHTML == null)
		{
			return error();
		}
		
		return Response.ok(vizHTML).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	}
	
	private Response error()
	{
		return Response.serverError()
				.header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .build();
	}
}