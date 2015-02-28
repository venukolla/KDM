package com.ibm.scientists.model;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;





public class Scientist {

	public String name;
	public BioInfo bioinformation;
	public List<ResearchInterest> researchInterest; 
	
	public String image_url;
	
	public Scientist() {
		
	}
	
	public Scientist(String name, BioInfo bioinformation, List<ResearchInterest> researchInterest, String url){
		
		this.name = name;
		this.bioinformation = bioinformation;
		this.researchInterest = researchInterest;
		this.image_url = url;
	}
	
	public List<FollowUp> getFollowUp() {
		return Collections.singletonList(new FollowUp(name, image_url));
	}
}
