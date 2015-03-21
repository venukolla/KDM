package com.ibm.scientists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ibm.scientists.db.CloudantDatabase;
import com.ibm.scientists.model.Scientist;

/*import com.ibm.personafusion.controller.SearchController;
import com.ibm.personafusion.db.CloudantClient;
import com.ibm.personafusion.model.Person;*/

public class Engine 
{
	List<Scientist> scientists;
	
	public Engine(List<Scientist> people)
	{
		//this.people = people;
		
		this.scientists = new ArrayList<Scientist>();
		for(Scientist p : people)
		{
			this.scientists.add(new Scientist(p.name, p.bioinformation,p.researchInterest, p.image_url));
		}
	}
	
	List<Scientist> query(Scientist p)
	{
		this.setQueryPerson(p);
		this.setDistanceWeights(.5);
		Collections.sort(this.scientists);
		this.scientists.remove(0);
		this.convertScores(this.scientists);
		return this.scientists;
	}
	
	public List<Scientist> query(String personName)
	{
		personName = personName.toUpperCase();
		
		
		//get person with the person name

		// Here's the changed new code
		CloudantDatabase cc = new CloudantDatabase();
		for (Scientist p: scientists) {
			Scientist queriedPer = cc.getScientist(personName);
			if(queriedPer == null) System.out.println("queriedPerson is null");
			p.setQueryPerson(queriedPer);
//			p.setDistanceWeights(.5,  0, .5);
		}
		
		
		//this.setQueryPerson(this.getPersonGivenName(personName));
		//System.out.println("Set person's name: " + this.people.get(0).name);
		//this.setDistanceWeights(.5, 0 , .5);
		
		Collections.sort(this.scientists);
		
		// this.people.remove(0);
		
		this.convertScores(this.scientists);
		return this.scientists;
	}
	
	Scientist getPersonGivenName(String personName)
	{
		for(Scientist p : this.scientists)
			if(p.name.equals(personName))
				return p;
		return null;
	}
	
	void convertScores(List<Scientist> people)
	{
		double minDist = Double.MAX_VALUE;
		for(Scientist p : people)
		{
			if(minDist > p.distToQueryPerson)
				minDist = p.distToQueryPerson;
		}
		
		for(Scientist p : people)
		{
			p.distToQueryPerson = (int) (minDist*100.0 / (p.distToQueryPerson));
		}
	}
	
	void setQueryPerson(Scientist p)
	{
		scientists.get(0).setQueryPerson(p);
	}
	
	void setDistanceWeights(double weightTraits)
	{
		scientists.get(0).setDistanceWeights(weightTraits);
	}
	
	
}
