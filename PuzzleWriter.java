import java.util.*;
import java.io.*;
public class PuzzleWriter {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("please enter a dimension: ");
		int dimension = Integer.parseInt(new Scanner(System.in).next());
		PrintWriter pw = new PrintWriter(new File("BigPuzzleFile.txt"));
		Random rand = new Random();
		pw.write(dimension + "\n");
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++) {
				pw.write(rand.nextInt(26) + 97);
				if (j != dimension - 1)
					pw.write(" ");
				else
					pw.write("\n");		
			}
	}
}