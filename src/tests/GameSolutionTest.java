package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Player;
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
	public void disproveSuggestion() {
		Card personSug = new Card(CardType.PERSON, "Draymond Green");
		Card weaponSug = new Card(CardType.WEAPON, "Chain");
		Card roomSug = new Card(CardType.ROOM, "Labratory");
		Solution suggestion = new Solution(roomSug, personSug, weaponSug);
		//If player has only one matching card it should be returned
		Player[] playerList = board.getPlayers();
		ComputerPlayer matchOne = (ComputerPlayer) playerList[1];
		Card wrongWeapon = new Card(CardType.WEAPON, "Football");
		Card wrongRoom = new Card(CardType.ROOM, "Coffee Shop");
		matchOne.updateHand(personSug);
		matchOne.updateHand(wrongWeapon);
		matchOne.updateHand(wrongRoom);
		assertTrue(matchOne.disproveSuggestion(suggestion).equals(personSug));
		
		//If players has >1 matching card, returned card should be chosen randomly
		ComputerPlayer matchMult = (ComputerPlayer) playerList[4];
		wrongRoom = new Card(CardType.ROOM, "Game Room");
		matchMult.updateHand(personSug);
		matchMult.updateHand(weaponSug);
		matchMult.updateHand(wrongRoom);
		for (int i=0; i < 10; i++) {
			Card disprove = matchMult.disproveSuggestion(suggestion);
			if (disprove.equals(null) || disprove.equals(wrongRoom)) {
				fail();
			}
		}
		//If player has no matching cards, null is returned
		ComputerPlayer noMatch = (ComputerPlayer) playerList[3];
		Card wrongPerson = new Card(CardType.PERSON, "Russell Wilson");
		
		noMatch.updateHand(wrongPerson);
		noMatch.updateHand(wrongRoom);
		noMatch.updateHand(wrongWeapon);
		
		assertEquals(noMatch.disproveSuggestion(suggestion), null);
	}
	
	@Test
	public void handleSuggestions() {
//		Suggestion no one can disprove returns null
//		board.deal();
//		Solution answer = board.getTheAnswer();
//		
		Player[] players = board.getPlayers();
		Card compRoom = new Card(CardType.ROOM, "Computer Lab");
		Card football = new Card(CardType.WEAPON, "Football");
		Card joebrr = new Card(CardType.PERSON, "Joe Brrr");
		Card gameRoom = new Card(CardType.ROOM, "Game Room");
		Card library = new Card(CardType.ROOM, "Library");
		Card draymondGreen = new Card(CardType.PERSON, "Draymond Green");
		Card needle = new Card(CardType.WEAPON, "Needle");
		Card jj = new Card(CardType.PERSON, "Justin Jefferson");
		Card chain = new Card(CardType.WEAPON, "Chain");
		
		Card russ = new Card(CardType.PERSON, "Russell Wilson");
		Card machine = new Card(CardType.ROOM, "Machine Shop");
		Card glove = new Card(CardType.WEAPON, "Glove");
		
		Solution suggestion = new Solution(machine, russ, glove);
		
		players[0].updateHand(compRoom);
		players[0].updateHand(football);
		players[0].updateHand(joebrr);
		players[1].updateHand(gameRoom);
		players[1].updateHand(draymondGreen);
		players[1].updateHand(needle);
		players[2].updateHand(jj);
		players[2].updateHand(library);
		players[2].updateHand(chain);

		assertEquals(board.handleSuggestion(suggestion, players[0]), null);
		
		

//		Suggestion only suggesting player can disprove returns null
		
		suggestion = new Solution(compRoom, joebrr, football);
		assertEquals(board.handleSuggestion(suggestion, players[0]), null);
//		Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
		suggestion = new Solution(compRoom, draymondGreen, needle);
		assertEquals(board.handleSuggestion(suggestion, players[1]), compRoom);
//		Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
		suggestion = new Solution(gameRoom, jj, glove);
		assertEquals(board.handleSuggestion(suggestion, players[0]), gameRoom);
	}
}
