/*
 * BoardTestsExp: Runs Adjacency and Target tests on Experiment Board
 * 
 * Authors: Mathew Grossman, Julian Reyes
 */
package tests;

import experiment.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTestsExp {
	TestBoard board;
	@BeforeEach
	public void setUp() {
		board = new TestBoard();
	}
	
	
	
	/*
	 * Adjacency tests. Tests
	 * Adjacency for corner, edge, and center cells.
	 */
	@Test
	public void testAdjacency() {
		
//		Top Left
		TestBoardCell cell = board.getCell(0, 0);
		Set<TestBoardCell> adjList = cell.getAdjList();
		assertTrue(adjList.contains(board.getCell(1, 0)));
		assertTrue(adjList.contains(board.getCell(0, 1)));
		assertEquals(2, adjList.size());
		
//		Bottom Right
		cell = board.getCell(3, 3);
		adjList = cell.getAdjList();
		assertTrue(adjList.contains(board.getCell(2, 3)));
		assertTrue(adjList.contains(board.getCell(3, 2)));
		assertEquals(2, adjList.size());
//		Left Edge
		cell = board.getCell(2, 0);
		adjList = cell.getAdjList();
		assertTrue(adjList.contains(board.getCell(1, 0)));
		assertTrue(adjList.contains(board.getCell(2, 1)));
		assertTrue(adjList.contains(board.getCell(3,0)));
		assertEquals(3, adjList.size());
		
//		Right Edge
		cell = board.getCell(1, 3);
		adjList = cell.getAdjList();
		assertTrue(adjList.contains(board.getCell(1, 2)));
		assertTrue(adjList.contains(board.getCell(0, 3)));
		assertTrue(adjList.contains(board.getCell(2,3)));
		assertEquals(3, adjList.size());
		
//		Middle Cell
		cell = board.getCell(2, 2);
		adjList = cell.getAdjList();
		assertTrue(adjList.contains(board.getCell(1, 2)));
		assertTrue(adjList.contains(board.getCell(2, 1)));
		assertTrue(adjList.contains(board.getCell(2,3)));
		assertTrue(adjList.contains(board.getCell(3, 2)));
		assertEquals(4, adjList.size());
		
	}
	
	/*
	 * testTargets: Checks that calcTargets calculates valid moves properly.
	 * Tests show current layout above. 
	 * 
	 * Legend:
	 * X = occupied cell
	 * C = Cell under test
	 * R = room
	 * O = open cell
	 */
	@Test
	public void testTargets() {
		
		/*
		 * Board Layout:
		 * C O O O
		 * O O O O
		 * O O O O
		 * O O O O
		 */
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 5);
		Set<TestBoardCell> targetList = board.getTargets();
		assertEquals(8,targetList.size());
		assertTrue(targetList.contains(board.getCell(3, 2)));
		assertTrue(targetList.contains(board.getCell(2, 1)));
		assertTrue(targetList.contains(board.getCell(2, 3)));
		assertTrue(targetList.contains(board.getCell(0, 3)));
		assertTrue(targetList.contains(board.getCell(0, 1)));
		assertTrue(targetList.contains(board.getCell(1, 0)));
		assertTrue(targetList.contains(board.getCell(1, 2)));
		assertTrue(targetList.contains(board.getCell(3, 0)));
		
		targetList.clear();
		/*
		 * Board Layout:
		 * O O O O
		 * O O C O
		 * O O R O
		 * O O O O
		 */
		board.getCell(2, 2).setRoom(true);
		cell = board.getCell(1, 2);
		board.calcTargets(cell, 3);
		targetList = board.getTargets();
		assertEquals(8, targetList.size());
		assertTrue(targetList.contains(board.getCell(2, 2)));
		assertTrue(targetList.contains(board.getCell(3, 3)));
		assertTrue(targetList.contains(board.getCell(0, 0)));
		assertTrue(targetList.contains(board.getCell(1, 3)));
		assertTrue(targetList.contains(board.getCell(1, 1)));
		assertTrue(targetList.contains(board.getCell(2, 0)));
		assertTrue(targetList.contains(board.getCell(3, 1)));
		assertTrue(targetList.contains(board.getCell(0, 2)));
		
		targetList.clear();
		/*
		 * Layout:
		 * O O O O
		 * O O X O
		 * O C R O
		 * O O O O
		 */
		board.getCell(1, 2).setOccupied(true);
		cell = board.getCell(2, 1);
		board.calcTargets(cell, 2);
		targetList = board.getTargets();
		assertEquals(5, targetList.size());
		assertTrue(targetList.contains(board.getCell(3, 0)));
		assertTrue(targetList.contains(board.getCell(1, 0)));
		assertTrue(targetList.contains(board.getCell(2, 2)));
		assertTrue(targetList.contains(board.getCell(0, 1)));
		assertTrue(targetList.contains(board.getCell(3, 2)));
		
		targetList.clear();
		
		/*
		 * Layout:
		 * O O O O
		 * O O R O
		 * X C X O
		 * O O O O
		 */
		board.getCell(2, 0).setOccupied(true);
		board.calcTargets(cell, 6);
		targetList = board.getTargets();
		assertEquals(3,targetList.size());
		assertTrue(targetList.contains(board.getCell(0, 3)));
		assertTrue(targetList.contains(board.getCell(2, 3)));
		assertTrue(targetList.contains(board.getCell(2,  2)));
		
	}
	

}
