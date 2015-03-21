package com.ibm.scientists.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.scientists.Config;
import com.ibm.scientists.Constants;
import com.ibm.scientists.controller.JsonUtils;
import com.ibm.scientists.model.BioInfo;
import com.ibm.scientists.model.Qualification;
import com.ibm.scientists.model.ResearchInterest;
import com.ibm.scientists.model.Scientist;


public class CloudantDatabase {
	
	private org.ektorp.http.HttpClient httpClient;
	private CouchDbConnector dbc;
	
	private int port;
	private String name;
	private String host;
	private String username;
	private String password;
	
	private JSONArray cloudant;
	private JSONObject cloudantInstance;
	private JSONObject cloudantCredentials;
	
	public CloudantDatabase() 
	{
		this.httpClient = null;
		 
		 /*try {
           		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
			JSONObject vcap;
			vcap = (JSONObject) JSONObject.parse(VCAP_SERVICES);
			
			cloudant = (JSONArray) vcap.get("cloudantNoSQLDB");
			cloudantInstance = (JSONObject) cloudant.get(0);
			cloudantCredentials = (JSONObject) cloudantInstance.get("credentials");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		this.port = Config.CLOUDANT_PORT;
		this.host = (String) cloudantCredentials.get("host");
		this.username = (String) cloudantCredentials.get("username");
		this.password = (String) cloudantCredentials.get("password");
		this.name = Config.CLOUDANT_NAME; */
		
		this.port = 443;
		this.host = "cdb3ec23-5329-4ca8-8311-2b51c082de1d-bluemix.cloudant.com";
		this.username = "cdb3ec23-5329-4ca8-8311-2b51c082de1d-bluemix";
		this.password = "5b28c6e63bf36fac37c2dbf09c634200d1aff46656a69ad0a1d0ee0b22bbef05";
		this.name = Config.CLOUDANT_NAME;
		
		this.dbc = this.createDBConnector();
		
		
	}

	private CouchDbConnector createDBConnector() {
CouchDbInstance dbInstance = null;
		
		System.out.println("Creating CouchDB instance...");
		System.out.println(this.username);
		this.httpClient = new StdHttpClient.Builder().host(this.host)
		.port(this.port)
		.username(this.username)
		.password(this.password)
		.enableSSL(true)
		.relaxedSSLSettings(true)
		.build();

		dbInstance = new StdCouchDbInstance(this.httpClient);
		CouchDbConnector dbc = new StdCouchDbConnector(this.name, dbInstance);
		dbc.createDatabaseIfNotExists();
		return dbc;
	}

	private void closeDBConnector()
	{
		if (httpClient != null)
		{
			httpClient.shutdown();
		}
	}
	public static void main(String[] args) {
		
		List<Qualification> qualification = new ArrayList<Qualification>();
		
		Qualification qualify = new Qualification("PHD", "2012");
		Qualification qualify1 = new Qualification("MS", "2008");
		qualification.add(qualify);
		qualification.add(qualify1);
		
		Map<String, String> awardsMap = new HashMap<String, String>();
		
		awardsMap.put("Nobel Prize", "1995");
		
		BioInfo bioinformation = new BioInfo("Guntur", "02/10/15", qualification, awardsMap);
		
		List<ResearchInterest> researchIntrst = new ArrayList<ResearchInterest>();
		ResearchInterest resrchIntrst = new ResearchInterest("Physics");
		ResearchInterest resrchIntrst1 = new ResearchInterest("ISRO");
		researchIntrst.add(resrchIntrst);
		researchIntrst.add(resrchIntrst1);
		
				
		Scientist scientist = new Scientist("Venu Babu Kolla", bioinformation ,researchIntrst,"" );
		
		CloudantDatabase cdatabase = new CloudantDatabase();
		cdatabase.putScientist(scientist);
		
		//Scientist scientist1 = cdatabase.getScientist("")
		
	}

	private void putScientist(Scientist scientist) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		String name = scientist.name.toUpperCase();
		
		data.put(Constants.ID_KEY, name);
		
		//data.put(Constants.GROUP_KEY, group);
		//System.out.println(data.get(Constants.TYPE_KEY));
		data.put(Constants.JSON_KEY, JsonUtils.getJson(scientist));
		this.putItem(data);
	}

	private void putItem(HashMap<String, Object> data) {
		if (data == null) 
		{ 
			System.err.println("data cannot be null in putItem()"); 
			return;
		}
		String id = (String)data.get(Constants.ID_KEY);
		if (id == null)   
		{ 
			System.err.println("data must have an _id field."); 
			return;
		}
		if (this.dbc.contains(id)) 
		{ 
			System.err.println("Didn't putItem. _id=" + id + " already exists."); 
			return;
		}
		this.dbc.create(data);
		System.out.println("Put _id=" + id + " into the datastore."); 
		
	}
	
	/**
	 * 
	 * 
	 *  Get a Scientist Name from Cloudant using name as the unique id. 
	 * 
	 * **/
	public Scientist getScientist(String name)
	{
		name = name.toUpperCase();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> obj = this.dbc.get(HashMap.class, name);
		Scientist scientist = JsonUtils.getPersonFromJson((String)obj.get(Constants.JSON_KEY));
		return scientist;
	}

	public List<Scientist> getAllScientistsNotInGroup(String currentScientistsGroup) {
		// TODO Auto-generated method stub
		return null;
	}
}
