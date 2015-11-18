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
		File dictionaryFile = new File(args[1]);
		Dictionary dictionary = new Dictionary(dictionaryFile);
		//board is a wrapper for the string and dimension
		File puzzleGraphFile = new File(args[0]);
		Board board = new Board(puzzleGraphFile);
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