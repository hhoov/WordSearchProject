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
		Dictionary dictionary = new Dictionary(args[0]);
		//board is a wrapper for the string and dimension
		Board board = new Board(args[1]);
		//searcher creates the adjacency list
		//and performs DFS
		Searcher searcher = new Searcher(board, dictionary);
		List<String> wordsFound = searcher.findWords();
		for (String s : wordsFound)
			System.out.println(s);
	}
}