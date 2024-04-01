package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class GameSolutionTest {
	
	public static Board board;
	@BeforeEach
	public void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}
	
	
	@Test
	public void accussationTest() {
//		Wrong Person
//		Wrong Room
//		Wrong Weapon
//		Correct
	}
	
	@Test
	public void disproveSolution() {
		
	}
	
	@Test
	public void handleSuggestions() {
		
	}
}
