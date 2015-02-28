package com.ibm.scientists.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class AddScientistRequest {
	
	public String firstName;
	public String lastName;
	public String role;
	public Map<String, List<String>> bioInfo;
	public List<String> responses;
	public String imageUrl;
	public String group;

	public static Scientist toPerson(AddScientistRequest asr)
	{
		String fullName = asr.firstName + " " + asr.lastName;
		
		Scientist scientist = new Scientist(fullName, getBioInfo(asr), getResearchInterest(asr),asr.imageUrl);
		//scientist.qaResponses = asr.responses;
		//p.group = apr.group;
		//p.keyWords = p.getKeyWords(10);
		return scientist;
	}
	
	private static List<ResearchInterest> getResearchInterest(AddScientistRequest asr) {
		
		List<ResearchInterest> researchInterest = new ArrayList<ResearchInterest>();
		
		
		
		return researchInterest;
	}

	private static BioInfo getBioInfo(AddScientistRequest apr)
	{
		return null;
		/*// get traits
		WatsonUserModeller WUM = new WatsonUserModeller();
		String response = QuestionResponse.convertToFullString(apr.responses);
		List<Trait> traits = WUM.getTraitsList(response);
		return traits;*/
	}
	
	/*private static ResumeInfo constructResumeInfo(AddScientistRequest apr)
	{
		// construct ResumeInfo
		ResumeInfo ri = new ResumeInfo();
		ri.pastEmployers = apr.resumeInfo.get("pastEmployers");
		ri.techSkills = apr.resumeInfo.get("techSkills");
		return ri;
	}*/
}
