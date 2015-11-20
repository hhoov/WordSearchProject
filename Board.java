// class Board
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/19/15
// Class: CS 360
// Project 3: write	a program that given a n x n grid of letters and a list of valid 
// words, will find the valid words hidden within the puzzle.
// ********************

// Given a text file of lowercase letters separated by whitespace, with the
// first line giving the dimension of the graph (square matrix), builds a 
// character array of letters.
public class Board {
	int boardSize = 0;
	String stringOfLetters = "";
	char[] board = null;

	public Board(File puzzleGraphFile) throws IOException {
		String dataLine, sizeOfBoardLine = "";
		BufferedReader p = null;

		//Read first line, assigning integer to boardSize
		p = new BufferedReader(new FileReader(puzzleGraphFile));
		sizeOfBoardLine = p.readLine();
		boardSize = Integer.parseInt(sizeOfBoardLine);
		
		//Read each line and add each letter to the character
		//array board
		while ((dataLine = p.readLine()) != null) {
			// dataLine.replaceAll("\\s+","");
			for (int i = 0; i < dataLine.length(); i++) {
				if (dataLine.charAt(i) != ' ')
					stringOfLetters += dataLine.charAt(i);
			}
		}
		board = stringOfLetters.toCharArray();
	} // end of constructor Board

	//Setter for setting the dimension of the board
	public void setDimension(int boardSize) {
		this.boardSize = boardSize;
	}
	//Getter for getting the dimension of the board
	public int getDimension() {
		return boardSize;
	}
	//Getter for getting the letter from the character
	//array, board, at the specified index
	public char getLetter(int index) {
		return board[index];
	}
} // end of class Board