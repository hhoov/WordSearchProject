// class Dictionary
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/19/15
// Class: CS 360
// Project 3: write	a program that given a n x n grid of letters and a list of valid 
// words, will find the valid words hidden within the puzzle.
// ********************

// Given a text file of lowercase words, each on their own line, builds a 
// TreeSet of the words.
public class Dictionary {
	TreeSet<String> wordListTreeSet;
	public Dictionary(File wordFile) throws IOException {
		String dataLine = "";
		BufferedReader d = null;
		wordListTreeSet = new TreeSet<String>();

		//Read each line, take out any whitespace for each line,
		//and add each word to the TreeSet
		d = new BufferedReader(new FileReader(wordFile));
		while ((dataLine = d.readLine()) != null) {
			dataLine = dataLine.trim();
			wordListTreeSet.add(dataLine);
		}
	} // end of constructor Dictionary

	//If the built word is in the TreeSet, return true
	//otherwise, return false
	public boolean containsWord(String word) {
		return wordListTreeSet.contains(word);
	} // end of method containsWord

	//Returns true if the TreeSet contains a word for which the variable prefix is a prefix
	public boolean containsPrefix(String prefix) {
		String prefixCeiling;
		int endIndex;
		prefixCeiling = wordListTreeSet.ceiling(prefix);
		endIndex = prefix.length();
		//if (prefixCeiling != null && (prefix.equals(prefixCeiling.substring(0,endIndex)))) {
		if (prefixCeiling != null && (prefixCeiling.startsWith(prefix))) {
			return true;
		} else {return false;}		
	} // end of method containsPrefix
} // end of class Dictionary