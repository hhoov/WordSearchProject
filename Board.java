// class Board
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/18/15
// Class: CS 360
// Project 3:
// ********************

public class Board {
	int boardSize = 0;
	String temp = "";
	char[] board = null;
	public Board(File puzzleGraphFile) throws IOException {
		String dataLine, sizeOfBoardLine = "";
		//int boardSize = 0;
		BufferedReader p = null;

		//File puzzleGraphFile = new File(args[0]);
		p = new BufferedReader(new FileReader(puzzleGraphFile));
		sizeOfBoardLine = p.readLine();
		boardSize = Integer.parseInt(sizeOfBoardLine);
		
		while ((dataLine = p.readLine()) != null) {
			// dataLine.replaceAll("\\s+","");
			for (int i = 0; i < dataLine.length(); i++) {
				if (dataLine.charAt(i) != ' ')
					temp += dataLine.charAt(i);
			}
		}
		board = temp.toCharArray();
		//return board;
	} // end of constructor Board
	// public setDimension() {
	// 	boardSize = 
	// }

	public void setDimension(int boardSize) {
		this.boardSize = boardSize;
	}
	public int getDimension() {
		return boardSize;
	}

	public char getLetter(int index) {
		return board[index];
	}
} // end of class Board