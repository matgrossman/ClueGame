package tests;

import experiment.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
		TestBoardCell cell = board.getCell(0, 0);
		Set<TestBoardCell> adjList = cell.getAdjList();
		assertTrue(adjList.contains(board.getCell(1, 0)));
		assertTrue(adjList.contains(board.getCell(0, 1)));
		assertEquals(2, adjList.size());
		
		cell = board.getCell(2, 0);
		adjList = cell.getAdjList();
		assertTrue(adjList.contains(board.getCell(1, 0)));
		assertTrue(adjList.contains(board.getCell(2, 1)));
		assertTrue(adjList.contains(board.getCell(3,0)));
		assertEquals(3, adjList.size());
		
		cell = board.getCell(2, 2);
		adjList = cell.getAdjList();
		assertTrue(adjList.contains(board.getCell(1, 2)));
		assertTrue(adjList.contains(board.getCell(2, 1)));
		assertTrue(adjList.contains(board.getCell(2,3)));
		assertTrue(adjList.contains(board.getCell(3, 2)));
		assertEquals(4, adjList.size());
		
	}
	
	@Test
	public void testTargets() {
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 5);
		Set<TestBoardCell> targetList = board.getTargets();
		assertEquals(8,targetList.size());
		assertTrue(targetList.contains(board.getCell(3, 2)));
		assertTrue(targetList.contains(board.getCell(2, 1)));
		assertTrue(targetList.contains(board.getCell(1, 1)));
		assertTrue(targetList.contains(board.getCell(2, 3)));
		assertTrue(targetList.contains(board.getCell(0, 3)));
		assertTrue(targetList.contains(board.getCell(0, 1)));
		assertTrue(targetList.contains(board.getCell(1, 0)));
		assertTrue(targetList.contains(board.getCell(1, 2)));

		board.getCell(2, 2).setOccupied(true);
		cell = board.getCell(1, 2);
		board.calcTargets(cell, 3);
		targetList = board.getTargets();
		assertEquals(8, targetList.size());
		assertTrue(targetList.contains(board.getCell(2,2)));
		assertTrue(targetList.contains(board.getCell(3, 3)));
		assertTrue(targetList.contains(board.getCell(0, 0)));
		assertTrue(targetList.contains(board.getCell(0, 3)));
		assertTrue(targetList.contains(board.getCell(1, 3)));
		assertTrue(targetList.contains(board.getCell(1, 1)));
		assertTrue(targetList.contains(board.getCell(0, 2)));
		assertTrue(targetList.contains(board.getCell(3, 1)));
		
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
		



		
		
		
	}
}
