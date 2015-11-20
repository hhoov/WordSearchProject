// class WordSearch
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/19/15
// Class: CS 360
// Project 3: write	a program that given a n x n grid of letters and a list of valid 
// words, will find the valid words hidden within the puzzle.
// ********************

public class WordSearch
{
	public static void main(String[] args){
		try {
			// dictionary is a wrapper for the treeset/word list
			File dictionaryFile = new File(args[1]);
			Dictionary dictionary = new Dictionary(dictionaryFile);
			// board is a wrapper for the string and dimension
			File puzzleGraphFile = new File(args[0]);
			Board board = new Board(puzzleGraphFile);
			// searcher creates the adjacency list
			// and performs DFS
			Searcher searcher = new Searcher(board, dictionary);
			System.out.println("Words:");
			List<String> wordsFound = searcher.findWords();
			for (String s : wordsFound)
				System.out.print(s);
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
	} // end of main
} // end of class WordSearch