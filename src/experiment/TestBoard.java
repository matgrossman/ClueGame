/*
 * TestBoard: Stores test 4x4 board and runs target calculations
 * Authors: Julian Reyes, Mathew Grossman
 */
package experiment;

import java.util.HashSet;
import java.util.Set;


public class TestBoard {
	
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	
	final static int COLS = 4;
	final static int ROWS = 4;
	/** 
	 * Default Constructor: Initializes Test Board
	 */	
	public TestBoard() {
		super();
		
		grid = new TestBoardCell[ROWS][COLS];
		targets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				grid[i][j] = new TestBoardCell(i,j);
			}
		}
		
//		Generate adjacency list
		
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				TestBoardCell cell = this.getCell(row, col);
				
//				Test for each edge. If not on an edge, there is an adjacency in that direction
				if(row > 0) {
					cell.addAdjacency(this.getCell(row - 1, col));
				}
				if(row + 1 < ROWS) {
					cell.addAdjacency(this.getCell(row + 1, col));
				}
				
				if(col > 0) {
					cell.addAdjacency(this.getCell(row, col - 1));
				}
				if(col + 1 < ROWS) {
					cell.addAdjacency(this.getCell(row, col + 1));
				}

			}
		}
	}
	
	/** 
	 * calcTargets: calculates legal targets for a move from startCell of length pathlength.
	 * 
	 */
	
	public void calcTargets( TestBoardCell startCell, int pathlength) {
		targets.clear();
		targetCalc(startCell, pathlength);
		return;
	}
	
	private void targetCalc(TestBoardCell startCell, int pathlength) {
		if(pathlength == 0) {
			targets.add(startCell);
			visited.clear();
			return;
		}
		
		
		for(TestBoardCell cell : startCell.getAdjList()) {
			if(visited.contains(cell)) {
				continue;
			}
			
			visited.add(cell);
			
			if(cell.isOccupied()) {
				continue;
			}
			
			if(cell.isRoom()) {
				this.targetCalc(cell, 0);
				return;			
			}
			
			this.targetCalc(cell, pathlength - 1);
		}
		
	}
	/** 
	 *  getCell: returns the cell from the board at row, col.
	 * 
	 */
	
	public TestBoardCell getCell(int row, int col) {
		
		return grid[row][col];	
	}
	
	/** 
	 *   getTargets: gets the targets last created by calcTargets().
	 * 
	 */
	
	public Set<TestBoardCell> getTargets() {
		return targets;
		
	}

}
