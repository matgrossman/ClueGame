package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class ComputerAITest {
	
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
	public void createSuggestion() {
//		One weapon unseen
//		Multiple weapons unseen
//		One person unseen
//		Multiple people unseen
	}
	
	@Test
	public void selectTarget() {
//		No rooms
//		Room unseen
//		Room seen
	}
	
	
}
