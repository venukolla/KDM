package com.ibm.scientists.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class BioInfo {

	String birthplace;
	String dobirth;
	List<Qualification> qualifications;
	
	Map<String, String> awardsReceived = new HashMap<String, String>();
	
	public BioInfo() {
		
		this.birthplace = new String();
		this.dobirth = new String();
		this.qualifications = new ArrayList<Qualification>();
		this.awardsReceived = new HashMap<String, String>();
	}
	
	public BioInfo(String boPlace, String dob, List qualifications, Map<String, String> awardsMap) {
		
		this.birthplace = boPlace;
		this.dobirth = dob;
		this.qualifications = qualifications;
		this.awardsReceived = awardsMap; 
		
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getDobirth() {
		return dobirth;
	}

	public void setDobirth(String dobirth) {
		this.dobirth = dobirth;
	}
	
	void addQualifications(Qualification qulification)
	{
		this.qualifications.add(qulification);
	}
	
	void addAwardsReceived(String awardName, String year)
	{
		this.awardsReceived.put(awardName,year);
	}
	
}
