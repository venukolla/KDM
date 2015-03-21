package com.ibm.cloudoe.samples;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rita.wordnet.RiWordnet;


public class TestingNLP extends HttpServlet {
public static void main(String[] args) {
	 
	java.util.List<String> list = new ArrayList<String>();
	java.util.List<String> list1 = new ArrayList<String>();
	java.util.List<String> list2 = new ArrayList<String>();
	java.util.List<String> list3 = new ArrayList<String>();
	
	String word = "run";

	// Demo finding parts of speech
	String word9 = "Hello testing run program by sitting in chair";
	
	String dp[] = word9.split(" ");
	for (String string : dp) {
		System.out.println("inside the POS pling");
		String words[] = gettingPOS(string);
		
		list = Arrays.asList(words);
		for (String string1 : words) {
			System.out.println("words:"+string1);
			
		}
	}
	
	String words[] = gettingPOS(word);
	
	list = Arrays.asList(words);
	for (String string : words) {
		System.out.println("words:"+string);
		
	}
	
	String words1[] = gettingBestPOS(word);
	list1 = Arrays.asList(words1);
	
	for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
		String string = (String) iterator.next();
		System.out.println(string);
		
	}
	
	System.out.println("Getting synoniums");
	
	String words2[] = gettingSynonyms(word);
	list2 = Arrays.asList(words1);
	
	for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
		String string = (String) iterator.next();
		System.out.println(string);
		
	}
	
	String words3[] = gettingHypoSynonyms(word);
	list3 = Arrays.asList(words3);
	System.out.println("Hyposynomiums");
	for (Iterator iterator = list3.iterator(); iterator.hasNext();) {
		String string = (String) iterator.next();
		System.out.println(string);
		
	}
	/*for (String string : words1) {
		System.out.println("Bes words :"+string);
	}*/
}

@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dopost(req, resp);
	}

	public void dopost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HashMap<String, java.util.List<String>> hashmap1 = new HashMap<String, java.util.List<String>>();
		HashMap<String, java.util.List<String>> hashmap2 = new HashMap<String, java.util.List<String>>();
		HashMap<String, java.util.List<String>> hashmap3 = new HashMap<String, java.util.List<String>>();
		HashMap<String, java.util.List<String>> hashmap4 = new HashMap<String, java.util.List<String>>();
		
		java.util.List<String> lis = new ArrayList<String>();
		java.util.List<String> lis1 = new ArrayList<String>();
		java.util.List<String> lis2 = new ArrayList<String>();
		java.util.List<String> lis3 = new ArrayList<String>();
	
		String input = req.getParameter("input");
	
		System.out.println("Testing:"+ input);
	
		String ips[] = input.split(" ");
		for (String word : ips) {
			String words[] = gettingPOS(word);
			
			lis = Arrays.asList(words);
			
			hashmap1.put(word, lis);
		}
		
		/*String words[] = gettingPOS(word);
	
		lis = Arrays.asList(words);*/
	
		req.setAttribute("POSobject", hashmap1);
	
		for (String word : ips) {
			lis1 = Arrays.asList(gettingBestPOS(word));
			
			hashmap2.put(word, lis1);
		}
		//lis1 = Arrays.asList(gettingBestPOS(word));
	
		req.setAttribute("POSBobject", hashmap2);
		
		for (String word : ips) {
			lis2 = Arrays.asList(gettingSynonyms(word));
			
			hashmap3.put(word, lis2);
		}
		
		//lis2 = Arrays.asList(gettingSynonyms(word));
		
		req.setAttribute("POSHobject", hashmap3);
		
		for (String word : ips) {
			lis3 = Arrays.asList(gettingHypoSynonyms(word));
			
			hashmap4.put(word, lis2);
		}
		//lis3 = Arrays.asList(gettingHypoSynonyms(word));
		
		req.setAttribute("POSHSobject", hashmap4);
	
		req.getRequestDispatcher("start.jsp").forward(req, resp);
}

	

public static String[] gettingHypoSynonyms(String word) {
		RiWordnet wordnet = new RiWordnet(null);
		
		String hypoSyno = wordnet.getBestPos(word);
		
		String hypoSynos[] = wordnet.getAllHyponyms(word, hypoSyno);
		return hypoSynos;
	}

public static String[] gettingSynonyms(String word) {
		RiWordnet wordnt = new RiWordnet(null);
		
		String syno  = wordnt.getBestPos(word);
		
		String synos[]= wordnt.getAllSynonyms(word, syno);
		return synos;
	}

public static String[] gettingPOS(String word){
	RiWordnet wordnet = new RiWordnet(null);

	// Demo finding parts of speech
	
	System.out.println("\nFinding parts of speech for " + word + ".");
	String[] partsofspeech = wordnet.getPos(word);
		
	return partsofspeech;
}

public static String[] gettingBestPOS(String word){
	RiWordnet wordnet = new RiWordnet(null);
	
	System.out.println("\n Finding Best Parts of speech for "+ word+".");
	
	String pos = wordnet.getBestPos(word);
	
	String glosses[] = wordnet.getAllGlosses(word, pos);
	
	return glosses;
}
}
