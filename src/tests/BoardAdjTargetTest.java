package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesRooms()
	{
		// we want to test a couple of different rooms.
		// First, the study that only has a single door but a secret room
		Set<BoardCell> testList = board.getAdjList(3, 23);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(26, 22)));
		assertTrue(testList.contains(board.getCell(3, 20)));
		
		// now test the ballroom (note not marked since multiple test here)
		testList = board.getAdjList(14, 19);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(14, 17)));
		assertTrue(testList.contains(board.getCell(17, 19)));
		
		// one more room, the kitchen
		testList = board.getAdjList(20, 23);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCell(22, 21)));
	}

	
	// Ensure door locations include their rooms and also additional walkways
	// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjacencyDoor()
	{
		Set<BoardCell> testList = board.getAdjList(9, 0);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(16, 3)));
		assertTrue(testList.contains(board.getCell(9, 1)));

		testList = board.getAdjList(19, 3);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(16, 3)));
		assertTrue(testList.contains(board.getCell(19, 2)));
		assertTrue(testList.contains(board.getCell(19, 4)));
		assertTrue(testList.contains(board.getCell(20, 3)));
		
		testList = board.getAdjList(3, 20);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(3, 23)));
		assertTrue(testList.contains(board.getCell(4, 20)));
		assertTrue(testList.contains(board.getCell(2, 20)));
		assertTrue(testList.contains(board.getCell(3, 19)));
	}
	
	// Test a variety of walkway scenarios
	// These tests are Dark Orange on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on bottom edge of board, just one walkway piece
		Set<BoardCell> testList = board.getAdjList(0, 6);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCell(1, 6)));
		
		// Test near a door but not adjacent
		testList = board.getAdjList(7, 2);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(7, 3)));
		assertTrue(testList.contains(board.getCell(7, 1)));
		assertTrue(testList.contains(board.getCell(8, 2)));
		assertTrue(testList.contains(board.getCell(6, 2)));

		// Test adjacent to walkways
		testList = board.getAdjList(8, 20);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCell(8, 21)));
		assertTrue(testList.contains(board.getCell(8, 19)));
		assertTrue(testList.contains(board.getCell(9, 20)));
		assertTrue(testList.contains(board.getCell(7, 20)));

		// Test next to closet
		testList = board.getAdjList(24,17);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCell(24, 18)));
		assertTrue(testList.contains(board.getCell(23, 17)));
	
	}
	
	
	// Tests out of room center, 1, 3 and 4
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsInComputerLab() {
		// test a roll of 1
		board.calcTargets(board.getCell(2, 2), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(5, 1)));
		assertTrue(targets.contains(board.getCell(5, 2)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(2, 2), 3);
		targets= board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(6, 1)));
		assertTrue(targets.contains(board.getCell(7, 2)));	
		assertTrue(targets.contains(board.getCell(5, 4)));
		assertTrue(targets.contains(board.getCell(5, 3)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(2, 2), 4);
		targets= board.getTargets();
		assertEquals(14, targets.size());
		assertTrue(targets.contains(board.getCell(8, 1)));
		assertTrue(targets.contains(board.getCell(5, 5)));	
		assertTrue(targets.contains(board.getCell(5, 2)));
		assertTrue(targets.contains(board.getCell(7, 3)));	
	}
	
	@Test
	public void testTargetsInOffice() {
		// test a roll of 1
		board.calcTargets(board.getCell(20, 23), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCell(22, 21)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(20, 23), 3);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(22, 19)));
		assertTrue(targets.contains(board.getCell(23, 20)));	
		assertTrue(targets.contains(board.getCell(23, 22)));
		assertTrue(targets.contains(board.getCell(22, 23)));
		
		// test a roll of 4
		board.calcTargets(board.getCell(20, 23), 4);
		targets= board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(22, 18)));
		assertTrue(targets.contains(board.getCell(23, 23)));	
		assertTrue(targets.contains(board.getCell(26, 22)));
		assertTrue(targets.contains(board.getCell(23, 21)));	
	}

	// Tests out of room center, 1, 3 and 4
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsAtDoor() {
		// test a roll of 1, at door
		board.calcTargets(board.getCell(23, 20), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(26, 22)));
		assertTrue(targets.contains(board.getCell(23, 21)));	
		assertTrue(targets.contains(board.getCell(23, 19)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(23, 20), 3);
		targets= board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCell(23, 17)));
		assertTrue(targets.contains(board.getCell(24, 18)));
		assertTrue(targets.contains(board.getCell(21, 19)));	
		assertTrue(targets.contains(board.getCell(22, 22)));
		assertTrue(targets.contains(board.getCell(23, 23)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(23, 20), 4);
		targets= board.getTargets();
		assertEquals(14, targets.size());
		assertTrue(targets.contains(board.getCell(20, 23)));
		assertTrue(targets.contains(board.getCell(26, 22)));
		assertTrue(targets.contains(board.getCell(20, 19)));	
		assertTrue(targets.contains(board.getCell(23, 18)));
		assertTrue(targets.contains(board.getCell(23, 24)));	
	}

	@Test
	public void testTargetsInWalkway1() {
		// test a roll of 1
		board.calcTargets(board.getCell(19, 0), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCell(19, 1)));
		
		// test a roll of 3
		board.calcTargets(board.getCell(19, 0), 3);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(19, 3)));
		assertTrue(targets.contains(board.getCell(20, 2)));
		
		// test a roll of 4
		board.calcTargets(board.getCell(19, 0), 4);
		targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCell(16, 3)));
		assertTrue(targets.contains(board.getCell(19, 2)));
		assertTrue(targets.contains(board.getCell(20, 3)));	
		assertTrue(targets.contains(board.getCell(19, 4)));	
	}

	@Test
	public void testTargetsInWalkway2() {
		// test a roll of 1
		board.calcTargets(board.getCell(3, 19), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(3, 20)));
		assertTrue(targets.contains(board.getCell(2, 19)));	
		assertTrue(targets.contains(board.getCell(4, 19)));	
		
		// test a roll of 3
		board.calcTargets(board.getCell(3,19), 3);
		targets= board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(6, 19)));
		assertTrue(targets.contains(board.getCell(5, 20)));
		assertTrue(targets.contains(board.getCell(3, 23)));	
		
		// test a roll of 4
		board.calcTargets(board.getCell(3, 19), 4);
		targets= board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(6, 20)));
		assertTrue(targets.contains(board.getCell(4, 20)));
		assertTrue(targets.contains(board.getCell(0, 20)));	
	}

	@Test
	// test to make sure occupied locations do not cause problems
	public void testTargetsOccupied() {
		// test a roll of 4 blocked 2 down
		board.getCell(25, 7).setOccupied(true);
		board.calcTargets(board.getCell(23, 7), 4);
		board.getCell(25, 7).setOccupied(false);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCell(20, 6)));
		assertTrue(targets.contains(board.getCell(21, 7)));
		assertTrue(targets.contains(board.getCell(20, 8)));	
		assertTrue( targets.contains( board.getCell(21, 9))) ;
		assertTrue( targets.contains( board.getCell(25, 3))) ;
	
		// we want to make sure we can get into a room, even if flagged as occupied
		board.getCell(20, 23).setOccupied(true);
		board.getCell(22, 22).setOccupied(true);
		board.calcTargets(board.getCell(22, 21), 1);
		board.getCell(20, 23).setOccupied(false);
		board.getCell(22, 22).setOccupied(false);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(22, 20)));	
		assertTrue(targets.contains(board.getCell(23, 21)));	
		assertTrue(targets.contains(board.getCell(20, 23)));	
		
		// check leaving a room with a blocked doorway
		board.getCell(19, 12).setOccupied(true);
		board.calcTargets(board.getCell(24, 12), 3);
		board.getCell(19, 12).setOccupied(false);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(18, 12)));
		assertTrue(targets.contains(board.getCell(18, 14)));	
		assertTrue(targets.contains(board.getCell(19, 15)));

		
	}
}
