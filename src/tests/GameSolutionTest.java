package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Solution;

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
		Card personSol = new Card(CardType.PERSON, "Draymond Green");
		Card weaponSol = new Card(CardType.WEAPON, "Chain");
		Card roomSol = new Card(CardType.ROOM, "Labratory");
		Solution answer = new Solution(roomSol, personSol, weaponSol);
		
		board.setTheAnswer(answer);
		
//		Wrong Person
		Card wrongPerson = new Card(CardType.PERSON, "Russell Wilson");
		Solution wrongPersonAcc = new Solution(roomSol, wrongPerson, weaponSol);
		assertFalse(board.checkAccusation(wrongPersonAcc));
		
//		Wrong Room
		Card wrongRoom = new Card(CardType.ROOM, "Machine Shop");
		Solution wrongRoomAcc = new Solution(wrongRoom, personSol, weaponSol);
		assertFalse(board.checkAccusation(wrongRoomAcc));
//		Wrong Weapon
		Card wrongWeapon = new Card(CardType.WEAPON, "Book");
		Solution wrongWeaponAcc = new Solution(roomSol, personSol, wrongWeapon);
		assertFalse(board.checkAccusation(wrongWeaponAcc));
		
//		Correct
		Solution correctAcc = new Solution(roomSol, personSol, weaponSol);
		assertTrue(board.checkAccusation(correctAcc));
	}
	
	@Test
	public void disproveSolution() {
		//If player has only one matching card it should be returned
		//If players has >1 matching card, returned card should be chosen randomly
		//If player has no matching cards, null is returned
	}
	
	@Test
	public void handleSuggestions() {
//		Suggestion no one can disprove returns null
//		Suggestion only suggesting player can disprove returns null
//		Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
//		Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
	}
}
