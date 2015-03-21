package com.ibm.scientists.model;

import java.util.ArrayList;
import java.util.List;

public class ResearchInterest {
	
	String researchInterests;
	public double percentage;
	
	public ResearchInterest() {
		researchInterests = new String();
	}
	
	public ResearchInterest(String research){
		this.researchInterests = research;
	}
	
	public ResearchInterest( String id, double pct){
		this.researchInterests = id;
		this.percentage = pct;
	}
	
	public String getResearchInterests() {
		return researchInterests;
	}

	public void setResearchInterests(String researchInterests) {
		this.researchInterests = researchInterests;
	}

	
	
			
	/*public ResearchInterest(List<String> researchInterest){
		
		this.researchInterests = researchInterest;
	}
	
	void addResearchInterests(String reseachInterst)
	{
		this.researchInterests.add(reseachInterst);
	}*/
	
	

}
