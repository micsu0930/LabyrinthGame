package Labyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Maze {
	
	private int height;
	private int width;
	private int maze[][];
	
	

	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int[][] getMaze() {
		return maze;
	}

	public void setMaze(int[][] maze) {
		this.maze = maze;
	}
	
	

	public Maze(int height, int width) {
		super();
		this.height = height;
		this.width = width;
		
	}

	public void generateMaze() {
	    maze = new int [height][width];
	    // Initialize
	    for (int i = 0; i < height; i++)
	        for (int j = 0; j < width; j++)
	            maze[i][j] = 1;
	 
	     Random rand = new Random();
	     // r for row、c for column
	     // Generate random r
	     int r = rand.nextInt(height);
	     while (r % 2 == 0) {
	         r = rand.nextInt(height);
	     }
	     // Generate random c
	     int c = rand.nextInt(width);
	     while (c % 2 == 0) {
	         c = rand.nextInt(width);
	     }
	     // Starting cell
	     maze[r][c] = 0;
	 
	     //　Allocate the maze with recursive method
	     recursion(r, c);
	     
	     
	     
	 }
	 
	 private void recursion(int r, int c) {
	     // 4 random directions
	     Integer[] randDirs = generateRandomDirections();
	     // Examine each direction
	     for (int i = 0; i < randDirs.length; i++) {
	 
	         switch(randDirs[i]){
	         case 1: // Up
	             //　Whether 2 cells up is out or not
	             if (r - 2 <= 0)
	                 continue;
	             if (maze[r - 2][c] != 0) {
	                 maze[r-2][c] = 0;
	                 maze[r-1][c] = 0;
	                 recursion(r - 2, c);
	             }
	             break;
	         case 2: // Right
	             // Whether 2 cells to the right is out or not
	             if (c + 2 >= width - 1)
	                 continue;
	             if (maze[r][c + 2] != 0) {
	                 maze[r][c + 2] = 0;
	                 maze[r][c + 1] = 0;
	                 recursion(r, c + 2);
	             }
	             break;
	         case 3: // Down
	             // Whether 2 cells down is out or not
	             if (r + 2 >= height - 1)
	                 continue;
	             if (maze[r + 2][c] != 0) {
	                 maze[r+2][c] = 0;
	                 maze[r+1][c] = 0;
	                 recursion(r + 2, c);
	             }
	             break;
	         case 4: // Left
	             // Whether 2 cells to the left is out or not
	             if (c - 2 <= 0)
	                 continue;
	             if (maze[r][c - 2] != 0) {
	                 maze[r][c - 2] = 0;
	                 maze[r][c - 1] = 0;
	                 recursion(r, c - 2);
	             }
	             break;
	         }
	     }
	 
	 }
	 

	 private Integer[] generateRandomDirections() {
	      ArrayList<Integer> randoms = new ArrayList<Integer>();
	      for (int i = 0; i < 4; i++)
	           randoms.add(i + 1);
	      Collections.shuffle(randoms);
	 
	     return randoms.toArray(new Integer[4]);
	 }
}
