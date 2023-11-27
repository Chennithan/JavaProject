package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeDriver {
	/**
	 * 
	 * @param start
	 * @param end   find a path through the maze if found, output the path from
	 *              start to end if not path exists, output a message stating so
	 * 
	 */
	// implement this method
	public static boolean depthFirstStack(MazeCell start, MazeCell end, MazeCell[][] cells, int[][] grid) {
		MazeStack<MazeCell> stack = new MazeStack<>();
	    stack.push(start);

	    while (!stack.isEmpty()) {
	        MazeCell current = stack.pop();

	        // Check if current cell is the end
	        if (current.equals(end)) {
	            return true;
	        }

	        // Mark current cell as visited
	        current.visit();

	        int row = current.getRow();
	        int col = current.getCol();

	        // Check and add unvisited neighbors
            addNeighborToStack(row - 1, col, grid, cells, stack); // North
            addNeighborToStack(row, col + 1, grid, cells, stack); // East
            addNeighborToStack(row + 1, col, grid, cells, stack); // South
            addNeighborToStack(row, col - 1, grid, cells, stack); // West
	    }

	    return false;
	}
	
	public static boolean depthFirstQueue(MazeCell start, MazeCell end, MazeCell[][] cells, int [][] grid) {
		MazeQueue<MazeCell> queue = new MazeQueue<>();
		queue.enqueue(start);
		
		while (!queue.isEmpty()) {
			MazeCell current = queue.dequeue();
			// Check if current cell is the end
			if(current.equals(end)) {
				return true;
			}
			current.visit();
			
			int row = current.getRow();
			int col = current.getCol();
			
			// Check and add unvisited neighbors
	        addNeighborToQueue(row - 1, col, grid, cells, queue); // North
	        addNeighborToQueue(row, col + 1, grid, cells, queue); // East
	        addNeighborToQueue(row + 1, col, grid, cells, queue); // South
	        addNeighborToQueue(row, col - 1, grid, cells, queue); // West
			
		}
		
		return false;
		
	}
    
	private static void addNeighborToStack(int row, int col, int[][] grid, MazeCell[][] cells, MazeStack<MazeCell> stack) {
        if (isValidCell(row, col, grid) && cells[row][col].unVisited()) {
            stack.push(cells[row][col]);
        }
    }
	
	private static void addNeighborToQueue(int row, int col, int[][] grid, MazeCell[][] cells, MazeQueue<MazeCell> queue) {
	    if (isValidCell(row, col, grid) && cells[row][col].unVisited()) {
	        queue.enqueue(cells[row][col]);
	    }
	}

	private static boolean isValidCell(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] != 0;
    }

	public static void main(String[] args) throws FileNotFoundException {

		// create the Maze from the file
		Scanner fin = new Scanner(new File("Maze.in.txt"));
		// read in the rows and cols
		int rows = fin.nextInt();
		int cols = fin.nextInt();

		// create the maze
		int[][] grid = new int[rows][cols];

		// read in the data from the file to populate
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = fin.nextInt();
			}
		}

		// look at it
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 3)
					System.out.print("S ");
				else if (grid[i][j] == 4)
					System.out.print("E ");
				else
					System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}

		// make a 2-d array of cells
		MazeCell[][] cells = new MazeCell[rows][cols];

		// populate with MazeCell obj - default obj for walls

		MazeCell start = new MazeCell(), end = new MazeCell();

		// iterate over the grid, make cells and set coordinates
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// make a new cell
				cells[i][j] = new MazeCell();
				// if it isn't a wall, set the coordinates
				if (grid[i][j] != 0) {
					cells[i][j].setCoordinates(i, j);
					// look for the start and end cells
					if (grid[i][j] == 3)
						start = cells[i][j];
					if (grid[i][j] == 4)
						end = cells[i][j];

				}

			}
		}

		// testing
		System.out.println("start:" + start + " end:" + end + "\n");

		// solve it!
		// depthFirst(start, end);
		boolean pathFoundStack = depthFirstStack(start, end, cells, grid);
        if (pathFoundStack) {
            System.out.println("Path found using a stack!\n");
        } else {
            System.out.println("No path exists using a stack.\n");
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].reset();
            }
        }
        
        boolean pathFoundQueue = depthFirstQueue(start, end, cells, grid);
        if (pathFoundQueue) {
            System.out.println("Path found using a queue!\n");
        } else {
            System.out.println("No path exists using a queue.\n");
        }
	}
}