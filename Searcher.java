// class Searcher
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/18/15
// Class: CS 360
// Project 3:
// ********************

public class Searcher {
	int boardSize;
	String word = "";
	int[][] adjacencyList;
	String[] cardinalDirectionsArray;
	TreeSet<String> foundWordsNoDuplicates = null;
	public void Searcher(Board board, Dictionary dictionary) {
		boardSize = board.getDimension();
		//Creating adjacency list
		adjacencyList = new int[boardSize*boardSize][8];
		//Creating string array holding cardinal directions
		cardinalDirectionsArray = new String[] {"NW","N","NE","W","E","SW","S","SE"};
	
		//Finding neighbors for each vertex and adding to the adjacency list
		int neighbor=0;
		for (int i=0; i<boardSize*boardSize; i++) {
			for (int j=0; j<8; j++) {
				neighbor = findNeighbors(boardSize, i, cardinalDirectionsArray[j]);
				adjacencyList[i][j] = neighbor;
			}
		}
	} // end of constructor Searcher

	// DFS search, using a stack, to find words in the puzzle graph
	public StringBuilder findWords() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < boardSize*boardSize; i++) {
			for (int j = 0; j < 8; j++) {
				Stack<Integer> stack = new Stack<>();
				word = "";
				stack.push(i);
				while (!stack.isEmpty()) {
					int k = stack.pop();
					if (k != -1 && dictionary.containsPrefix(dictionary, word) == true) {
						stack.push(adjacencyList[k][j]);
						word += board[k];
						if (wordListTreeSet.contains(word)) {
							if (!foundWordsNoDuplicates.contains(word)) {
							foundWordsNoDuplicates.add(word);
							output.append(word + "(" + ((i%boardSize)+1) + "," + ((i/boardSize)+1) + "," + cardinalDirectionsArray[j] + ")\n");	
							}
						}
					}
				} // end of while
			} // end of second for loop
		} // end of first foor loop
		return output;
	} // end of method findWords()

	public int findNeighbors(int boardSize, int tile, String cardinalDirection) {
		int answer = -8;
		switch (cardinalDirection) {
			case "NW":
				if ((indexOfN(tile,boardSize) < 0) || (tile == 0 || tile % boardSize == 0)) {
					answer = -1;
				} else {
					answer = indexOfNW(tile,boardSize);
				}
				break;
			case "N":
					// no top neighbors
				if ((indexOfN(tile,boardSize)) < 0) { 
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns N neighbor
					answer = indexOfN(tile,boardSize); 
				}
				break;
			case "NE":
				if ((indexOfN(tile,boardSize) < 0) 
					|| (tile == boardSize - 1 
						|| ((tile + 1) % boardSize == 0 
							&& tile >= boardSize))) {
					answer = -1;
				} else {
				// 	// returns NE neighbor
				 	answer = indexOfNE(tile,boardSize); 
				}		
				break;
			case "W":
					// no left neighbor
				if (tile == 0 || tile % boardSize == 0) {
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns W neighbor
					answer = tile - 1;
				}
				break;
			case "E":
					//no right neighbors
				if (tile == boardSize - 1 || ((tile + 1) % boardSize == 0 && tile >= boardSize)) {
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns E neighbor
					answer = tile + 1;
				}
				break;
			case "SW":
				if ((indexOfS(tile,boardSize) >= (boardSize*boardSize)) 
					|| (tile == 0 || tile % boardSize == 0)) {
					answer = -1;
				} else {
					// returns SW neighbor
					answer = indexOfSW(tile,boardSize);
				}
				break;
			case "S":
				// no bottom neighbors
				if (indexOfS(tile,boardSize) >= (boardSize*boardSize)) {
					answer = -1; // indicates no neighbor at that index
				} else {
					// returns S neighbor
					answer = indexOfS(tile,boardSize);
				}
				break;
			case "SE":
				if (indexOfS(tile,boardSize) >= boardSize*boardSize //is on bottom
					|| (tile == boardSize - 1 || ((tile + 1) % boardSize == 0 && tile >= boardSize))) { //is on E side
						answer = -1;
				} else { // return SE neighbor
					answer = indexOfSE(tile,boardSize);
				} 
				break;
		} // end of switch
			return answer;
	} // end of method findNeighbors()

	// More concise methods of finding the index of each neighbor, for easier readability
	public static int indexOfS(int tile, int n) {
		return tile + n;
	}
	public static int indexOfN(int tile, int n) {
		return tile - n;
	}
	public static int indexOfE(int tile, int n) {
		return tile + 1;
	}
	public static int indexOfW(int tile, int n) {
		return tile - 1;
	}
	public static int indexOfNW(int tile, int n) {
		return tile - n - 1;
	}
	public static int indexOfNE(int tile, int n) {
		return tile - n + 1;
	}
	public static int indexOfSW(int tile, int n) {
		return tile + n - 1;
	}
	public static int indexOfSE(int tile, int n) {
		return tile + n + 1;
	}
} // end of class Searcher