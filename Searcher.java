// class Searcher
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/19/15
// Class: CS 360
// Project 3: write	a program that given a n x n grid of letters and a list of valid 
// words, will find the valid words hidden within the puzzle.
// ********************

// Given a board and dictionary, utilizes DFS with a stack
// to build and find words in the board that are then
// checked to see if they exist in the dictionary.
public class Searcher {
	int boardSize;
	String word = "";
	int[][] adjacencyListForNeighbors;
	String[] cardinalDirectionsArray;
	TreeSet<String> foundWords;
	Board board;
	Dictionary dictionary;

	public Searcher(Board board, Dictionary dictionary) {
		this.board = board;
		this.dictionary = dictionary;
		boardSize = board.getDimension();
		//Creates adjacency list
		adjacencyListForNeighbors = new int[boardSize*boardSize][8];
		//Creates string array holding cardinal directions
		cardinalDirectionsArray = new String[] {"nw","n","ne","w","e","sw","s","se"};
	
		//Finds neighbors for each vertex and adds them to the adjacency list
		int neighbor=0;
		for (int i=0; i<boardSize*boardSize; i++) {
			for (int j=0; j<8; j++) {
				neighbor = findNeighbors(boardSize, i, cardinalDirectionsArray[j]);
				adjacencyListForNeighbors[i][j] = neighbor;
			}
		}
	} // end of constructor Searcher

	// DFS search, using a stack, to build & find words in the puzzle graph
	public List<String> findWords() {
		List<String> outputFoundWords = new ArrayList<String>();
		// TreeSet to hold all of the found words that are also in the dictionary
		foundWords = new TreeSet<String>(); 
		for (int i = 0; i < boardSize*boardSize; i++) {
			for (int j = 0; j < 8; j++) {
				Stack<Integer> stack = new Stack<>();
				word = "";
				// Push letter onto the stack.
				stack.push(i);
				while (!stack.isEmpty()) {
					// Pop letter from the stack.
					int k = stack.pop();
					// If the dictionary contains the prefix of the so far built word
					if (k != -1 && dictionary.containsPrefix(word) == true) {
						// push another letter, in the same cardinal direction, onto the stack.
						stack.push(adjacencyListForNeighbors[k][j]);
						// Build the word using the letter of the board at that index.
						word += board.getLetter(k);
						// If the dictionary contains the built word and
						if (dictionary.containsWord(word)) {
							// if the word is not in the TreeSet of foundWords or
							// the TreeSet is null,
							if (foundWords == null || !foundWords.contains(word)) {
								// add the word to the TreeSet of foundWords and
								foundWords.add(word);
								// format the output of foundWords to give coordinates
								// and cardinal direction of the built word.
								outputFoundWords.add(word + "(" + ((i%boardSize)+1) + "," + ((i/boardSize)+1) + "," + cardinalDirectionsArray[j] + ")\n");
							}
						}
					}
				} // end of while
			} // end of second for loop
		} // end of first foor loop
		return outputFoundWords;
	} // end of method findWords()

	// Finds the neighbors of a tile in every cardinal direction
	public int findNeighbors(int boardSize, int tile, String cardinalDirection) {
		// Answer is -8 when the tile doesn't have neighbors in that cardinal direction,
		// if it is an edge/corner tile.
		int answer = -8;
		switch (cardinalDirection) {
			case "nw":
				// If there are no top neighbors or 
				// if the tile is an edge tile on the left side of the board 
				if ((indexOfN(tile,boardSize) < 0) || (tile == 0 || tile % boardSize == 0)) {
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns NW neighbor
					answer = indexOfNW(tile,boardSize);
				}
				break;
			case "n":
				// Checks to see if the tile is a top tile
				if ((indexOfN(tile,boardSize)) < 0) { 
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns N neighbor
					answer = indexOfN(tile,boardSize); 
				}
				break;
			case "ne":
				// Checks to see if the tile is a top tile or 
				if ((indexOfN(tile,boardSize) < 0) 
					// right edge tile or
					|| (tile == boardSize - 1 
						// bottom, right edge tile 
						|| ((tile + 1) % boardSize == 0 
							&& tile >= boardSize))) {
					answer = -1; // indicates no neighbor at that index
				} else {
				// 	// returns NE neighbor
				 	answer = indexOfNE(tile,boardSize); 
				}		
				break;
			case "w":
				// Checks to see if the tile is a left edge tile
				if (tile == 0 || tile % boardSize == 0) {
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns W neighbor
					answer = tile - 1;
				}
				break;
			case "e":
				// Checks to see if the tile is a right edge tile or
				// a top right edge tile or a bottom right edge tile
				if (tile == boardSize - 1 || ((tile + 1) % boardSize == 0 && tile >= boardSize)) {
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns E neighbor
					answer = tile + 1;
				}
				break;
			case "sw":
				// Checks to see if the tile is a bottom edge tile
				if ((indexOfS(tile,boardSize) >= (boardSize*boardSize)) 
					// or is a left edge tile
					|| (tile == 0 || tile % boardSize == 0)) {
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns SW neighbor
					answer = indexOfSW(tile,boardSize);
				}
				break;
			case "s":
				// Checks to see if the tile is a bottom edge tile
				if (indexOfS(tile,boardSize) >= (boardSize*boardSize)) {
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns S neighbor
					answer = indexOfS(tile,boardSize);
				}
				break;
			case "se":
				// Checks to see if the tile is a bottom edge tile
				if (indexOfS(tile,boardSize) >= boardSize*boardSize 
					// or is a right edge tile
					|| (tile == boardSize - 1 || ((tile + 1) % boardSize == 0 && tile >= boardSize))) {
						answer = -1; // indicates no neighbor at that index
				} else { 
					// return SE neighbor
					answer = indexOfSE(tile,boardSize);
				} 
				break;
		} // end of switch
			return answer;
	} // end of method findNeighbors()

	// More concise methods of finding the index of each neighbor, for easier readability
	public int indexOfS(int tile, int n) {
		return tile + n;
	}
	public int indexOfN(int tile, int n) {
		return tile - n;
	}
	public int indexOfE(int tile, int n) {
		return tile + 1;
	}
	public int indexOfW(int tile, int n) {
		return tile - 1;
	}
	public int indexOfNW(int tile, int n) {
		return tile - n - 1;
	}
	public int indexOfNE(int tile, int n) {
		return tile - n + 1;
	}
	public int indexOfSW(int tile, int n) {
		return tile + n - 1;
	}
	public int indexOfSE(int tile, int n) {
		return tile + n + 1;
	}
} // end of class Searcher