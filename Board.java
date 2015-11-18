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
	public char[] Board(String[] args) {
		String dataLine, temp, sizeOfBoardLine = "";
		int boardSize = 0;
		BufferedReader p = null;
		char[] board = null;
		try {
			File puzzleGraphFile = new File(args[0]);
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
			return board;
		} // end of try

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

	} // end of constructor Board
	public int getDimension() {
		return boardSize;
	}
} // end of class Board