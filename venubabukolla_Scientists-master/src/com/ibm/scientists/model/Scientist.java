package com.ibm.scientists.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;











public class Scientist implements Comparable<Scientist>{

	static double weightResearchInterests;
	
	
	public String name;
	public BioInfo bioinformation;
	public List<ResearchInterest> researchInterest; 
	
	static Scientist queryPerson;
	
	public String image_url;
	
	//This will be the list of answers from the q & a of the person
	//index 0 response to question 1, index 1 response to question 2 etc...
	public List<String> qaResponses;
	public double distToQueryPerson;
	
	public Scientist() {
		
	}
	
	public Scientist(String name, BioInfo bioinformation, List<ResearchInterest> researchInterest, String url){
		
		this.name = name;
		this.bioinformation = bioinformation;
		this.researchInterest = researchInterest;
		this.image_url = url;
		this.qaResponses = new ArrayList<String>();
	}
	
	public List<FollowUp> getFollowUp() {
		return Collections.singletonList(new FollowUp(name, image_url));
	}

	public void setQueryPerson(Scientist p) {
		this.queryPerson = p;
		
	}

	public void setDistanceWeights(double weightTraits) {
		this.weightResearchInterests = weightTraits;
		
	}

	@Override
	public int compareTo(Scientist other) {
		
			//sort bases off min distance from query perosn
			//weight different distances between resume skills, traits, and role
			double thisDistance = this.getDistanceToQueryPerson();
			double otherDistance = other.getDistanceToQueryPerson();
			if(thisDistance < otherDistance)
				return -1;
			if(thisDistance > otherDistance)
				return 1;
			else
				return 0;
		
	}
	
	double getDistanceToQueryPerson()
	{
		
		double distance = 0, distanceReseachInterests = 0;
		
		for(int i=0; i<this.queryPerson.researchInterest.size(); i++)
		{
			String queryResearchInterest = this.queryPerson.researchInterest.get(i).researchInterests;
			double queryResearchPercent = this.queryPerson.researchInterest.get(i).percentage;
			distanceReseachInterests += this.getReserachInterestDistance(queryResearchInterest	, queryResearchPercent);
		}
		
		
		
		distance = weightResearchInterests * distanceReseachInterests;
		this.distToQueryPerson = distance;
		return distance;
	}

	public double getReserachInterestDistance(String queryResearchInterest, double queryResearchPercent) {
		double distance = 1;//worst case if the trait does not exist return max value 1
		for(int i=0; i<this.researchInterest.size(); i++)
		{
			if(queryResearchInterest.equals(this.researchInterest.get(i).researchInterests))
			{
				double tempDist = (queryResearchPercent/100.0) - (this.researchInterest.get(i).percentage/100.0);
				distance = Math.pow(tempDist, 2);
				break;//found trait distance break loop
			}
		}
		return distance;
		
	}
}
