// class Main
import java.io.*;
import java.util.*;
// ********************
// Name: Hannah Hoover
// Date: 11/18/15
// Class: CS 360
// Project 3:
// ********************

public class Main
{
	
	public static void main(String[] args) 
	{
		String dataLine, temp = "", sizeOfBoardLine = "";
		int boardSize = 0;
		BufferedReader p = null;
		BufferedReader d = null;
		char[] board = null;
		TreeSet<String> wordListTreeSet = null;
		TreeSet<String> foundWordsNoDuplicates = null;
		//Stack wordStack;
		try {

			// ~~~~~~~Character Array for Puzzle Graph~~~~~~~
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
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


			//~~~~~~~TreeSet for Word List~~~~~~~~
			wordListTreeSet = new TreeSet<String>();

			//do i need to sort the word list and then add them to the treeset?
			//adding according to length= home-homework-homeworker
			File dictionaryFile = new File(args[1]);
			d = new BufferedReader(new FileReader(dictionaryFile));
			while ((dataLine = d.readLine()) != null) {
				dataLine.trim();
				wordListTreeSet.add(dataLine);
			}
			System.out.println("treeset:");
			System.out.println(wordListTreeSet);
			System.out.println();
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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

		// ~~~~~~~~~~~~~Adjacency List~~~~~~~~~~~~~~~~~~~~~~~~
		int[][] adjacencyList;
		String[] cardinalDirections;

		adjacencyList = new int[boardSize*boardSize][8];
		cardinalDirections = new String[] {"NW","N","NE","W","E","SW","S","SE"};
	
		//public void fillAdjacencyList() {
		int neighbor=0;
		for (int i=0; i<boardSize*boardSize; i++) {
			for (int j=0; j<8; j++) {
				neighbor = findNeighbors(boardSize, i, cardinalDirections[j]);
				//**************************************
				//INSTEAD of checking here, when you try to build a word, check if the index number is negative;
				//if it is, then you can't go in that direction because the graph doesn't continue that way
				//if (neighbor < 0) {
				//	neighbor = NULL;
				//}
				//**************************************

				adjacencyList[i][j] = neighbor;
			}
		}
		//} // end of method fillAdjacencyList
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


		//~~~~~~~Printing Adjacency List of Neighbors~~~~~~~~
		int count=0;
		int index=0;
		System.out.println("adjacency list of neighbors:");
		for (int k=0;k<boardSize*boardSize;k++) {
			System.out.print("index " + index + ":");
			index++;
			for (int l=0;l<8;l++) {
				System.out.print(" "+ adjacencyList[k][l] + "  ");
			}
			System.out.println();
			count++;
		}
		System.out.println("count = " + count);
		// System.out.println();
		// System.out.println("treeset:");
		// System.out.println(wordListTreeSet);
		
		// System.out.println("adjacency list of neighbors:");
		// System.out.println(Arrays.deepToString(adjacencyList));
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~8=======D~~~~~~~~~~~~~~~~
		//DFS
		//for every tile on the board
			//for every direction
		System.out.println();
		System.out.println("Words:");
		String word = "";
		boolean isWord;
		StringBuilder output = new StringBuilder();
		//List<String> wordList = new ArrayList<String>();

		for (int i = 0; i < boardSize*boardSize; i++) {
			for (int j = 0; j < 8; j++) {
				Stack<Integer> stack = new Stack<>();
				word = "";
				stack.push(i);
				while (!stack.isEmpty()) {
					int k = stack.pop();
					if (k != -1 && isValidPrefix(wordListTreeSet, word) == true) {
						stack.push(adjacencyList[k][j]);
						word += board[k];
						if (wordListTreeSet.contains(word))
							 if (!foundWordsNoDuplicates.contains(word))
						{
							foundWordsNoDuplicates.add(word);
							output.append(word + "(" + ((i%boardSize)+1) + "," + ((i/boardSize)+1) + "," + cardinalDirections[j] + ")\n");	
						}
							
					}
				}
			}
		}
		System.out.println(output.toString());
		



	} // end of main method 

	//returns true if ts contains a word for which prefix is a prefix
	public static boolean isValidPrefix(TreeSet<String> ts, String prefix) {
		String prefixCeiling;
		int endIndex;
		prefixCeiling = ts.ceiling(prefix);
		endIndex = prefix.length();
		//if (prefixCeiling != null && (prefix.equals(prefixCeiling.substring(0,endIndex)))) {
		if (prefixCeiling != null && (prefixCeiling.startsWith(prefix))) {
			return true;
		} else {return false;}		
	}


	public static int findNeighbors(int boardSize, int tile, String cardinalDirection) {
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
	} // end of method findNeighbors

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
	//public static void wordSearchDFS(char[] letterGraph, int s) {
	//	for (int i=0; i<board.length;i++) {
	//		//visited[v] = false;
	//	}
	//	wordStack = new Stack();
	//	//wordStack.push();
	//} // end of method wordSearchDFS

} //end of class Main
