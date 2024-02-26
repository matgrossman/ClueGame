package experiment;

import java.util.HashSet;
import java.util.Set;


public class TestBoard {
	/** 
	 * Default Constructor: Initializes Test Board
	 */	
	public TestBoard() {
		super();
	}
	
	/** 
	 * calcTargets: calculates legal targets for a move from startCell of length pathlength.
	 * 
	 */
	
	public void calcTargets( TestBoardCell startCell, int pathlength) {
		return;
	}
	
	/** 
	 *  getCell: returns the cell from the board at row, col.
	 * 
	 */
	
	public TestBoardCell getCell(int row, int col) {
	TestBoardCell returnCell = new TestBoardCell(row, col);
		return returnCell;	
	}
	
	/** 
	 *   getTargets: gets the targets last created by calcTargets().
	 * 
	 */
	
	public Set<TestBoardCell> getTargets() {
		Set<TestBoardCell> returnSet = new HashSet <TestBoardCell>();
		return returnSet;
		
	}

}
