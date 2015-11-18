// class Dictionary
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/18/15
// Class: CS 360
// Project 3:
// ********************

public class Dictionary {
	public Dictionary(File wordFile) {
		String dataLine = "";
		BufferedReader d = null;
		TreeSet<String> wordListTreeSet = null;
		try {
			wordListTreeSet = new TreeSet<String>();

			//do i need to sort the word list and then add them to the treeset?
			//adding according to length= home-homework-homeworker
			//File dictionaryFile = new File(args[1]);
			d = new BufferedReader(new FileReader(wordFile));
			while ((dataLine = d.readLine()) != null) {
				dataLine.trim();
				wordListTreeSet.add(dataLine);
			}
			//System.out.println("treeset:");
			//System.out.println(wordListTreeSet);
			//System.out.println();
			//return wordListTreeSet;
		} //end of try
		catch (FileNotFoundException e)
		{
			System.err.println("File not found: " + e.getMessage());
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("I/O Exception: " + e.getMessage());
			System.exit(1);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.err.println("No command line argument given: " + e.getMessage());
			System.exit(1);
		}
	} // end of constructor Dictionary

	//returns true if ts contains a word for which prefix is a prefix
	public boolean containsPrefix(TreeSet<String> ts, String prefix) {
		String prefixCeiling;
		int endIndex;
		prefixCeiling = ts.ceiling(prefix);
		endIndex = prefix.length();
		//if (prefixCeiling != null && (prefix.equals(prefixCeiling.substring(0,endIndex)))) {
		if (prefixCeiling != null && (prefixCeiling.startsWith(prefix))) {
			return true;
		} else {return false;}		
	}
} // end of class Dictionary