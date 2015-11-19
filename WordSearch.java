// class WordSearch
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/18/15
// Class: CS 360
// Project 3:
// ********************

public class WordSearch
{
	public static void main(String[] args){
		try {
			File dictionaryFile = new File(args[1]);
			Dictionary dictionary = new Dictionary(dictionaryFile);
			//board is a wrapper for the string and dimension
			File puzzleGraphFile = new File(args[0]);
			Board board = new Board(puzzleGraphFile);

			
		}
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
		//searcher creates the adjacency list
		//and performs DFS
		Searcher searcher = new Searcher(board, dictionary);
		StringBuilder wordsFound = searcher.findWords();
		System.out.println("Words:");
		System.out.println(wordsFound.toString());
		//List<String> wordsFound = searcher.findWords();
		// for (String s : wordsFound)
		// 	System.out.println(s);
	}
}