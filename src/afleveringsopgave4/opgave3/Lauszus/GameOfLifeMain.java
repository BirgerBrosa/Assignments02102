package afleveringsopgave4.opgave3.Lauszus;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the main game of life class. This will create an instance of GameOfLife and draw in on the screen.
 * @author Simon Patrzalek, Mads Bornebusch and Kristian Lauszus.
 */
public class GameOfLifeMain {
	private static int gridSize = 100;
	
	// This array is used to set the initial state.
	private static int[][] initialState = {
		{1,0,1},
		{0,1,1},
		{0,1,0},
	};
	
	/**
	 * This will called at runtime. It will create and run an instance of game of life.
	 * @param args This String array is not used.
	 * @throws FileNotFoundException Called when the requested initial file doesn't exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		/*GameOfLife gol = new GameOfLife(initialState); // Use the array above to set the initial state
		gridSize = initialState[0].length;*/
		//GameOfLife gol = new GameOfLife(gridSize); // Create random game
		GameOfLife gol = new GameOfLife(openMatrix("glider_gun.gol")); // Read the initial state from a file
		System.out.println(gol); // Print the state as a matrix
		
		StdDraw.setCanvasSize(700, 700); // Adjust the size of the window
		StdDraw.setBorder(0.10); // We added this function ourself in order to adjust the border size
		StdDraw.setScale(0, gridSize-1); // Set size of the coordinate system
		StdDraw.setPenRadius(1.0/(double)gridSize);
		StdDraw.setPenColor(Color.BLACK);		
		
		for(int i = 1; i <= 500; i++) {
			StdDraw.show(0);
			StdDraw.clear();
			//StdDraw.textLeft(-1.5/(double)gridSize, gridSize+10.0/(double)gridSize, Integer.toString(i));
			
			for(int y = 0; y < gridSize; y++) {
				for(int x = 0; x < gridSize; x++) {
					if(gol.getState(x, y) == 1)
						StdDraw.point(x, y);
				}
			}
			StdDraw.show(25); // Sleep for 25ms
			gol.nextState();
		}
	}
	
	/**
	 * Used to open a file containing a n x n dimensional array with an initial state.
	 * @param filename The name of the file you want to open located in the "src/afleveringsopgave4/Files/gol" directory.
	 * @return Return a n x n dimensional array with all the values in the matrix.
	 * @throws FileNotFoundException Throws this error in case the file doesn't exist.
	 */
	private static int[][] openMatrix(String filename) throws FileNotFoundException {
		List<Integer> list = new ArrayList<Integer>();
		Scanner fileScanner = new Scanner(new File("src" + File.separator + "afleveringsopgave4" + File.separator + "Files" + File.separator + "gol" + File.separator + filename));
		
		while(fileScanner.hasNextInt())
			list.add(fileScanner.nextInt());
		
		gridSize = (int)Math.sqrt(list.toArray().length);
		int[][] matrix = new int[gridSize][gridSize]; // We assume that it's a n x n matrix
		
		int index = 0;
		for(int y = 0; y < gridSize; y++) {
			for(int x = 0; x < gridSize; x++)
				matrix[y][x] = list.get(index++);
		}
		
		return matrix;
	}	
}