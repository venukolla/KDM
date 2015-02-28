package com.ibm.scientists.model;

public class Qualification {

	public String qualificationName;
	public String year;
	
	public Qualification( String qualificationString, String year) {
		
		this.qualificationName = qualificationString;
		this.year = year;
	}
	
	public String toString()
	{
		return this.qualificationName + " , " + this.year;
	}
}
