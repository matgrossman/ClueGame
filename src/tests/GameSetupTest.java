package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Set;
import java.util.Stack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
public class GameSetupTest {
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
	
	@Test
	public void playerSetup() {
		
		Player[] players = board.getPlayers();
		
//		Test Player names
		assertTrue(players[0].getName().equals("Draymond Green"));
		assertTrue(players[1].getName().equals("Patrick Mahomes"));
		assertTrue(players[2].getName().equals("Justin Jefferson"));
		assertTrue(players[3].getName().equals("Josh Allen"));
		assertTrue(players[4].getName().equals("Joe Brrr"));
		assertTrue(players[5].getName().equals("Russell Wilson"));
		
//		Test Player colors
		assertTrue(players[0].getColor().equals("Green"));
		assertTrue(players[1].getColor().equals("Red"));
		assertTrue(players[2].getColor().equals("Purple"));
		assertTrue(players[3].getColor().equals("Blue"));
		assertTrue(players[4].getColor().equals("Orange"));
		assertTrue(players[5].getColor().equals("Yellow"));
		
//		Test Player Locations
		assertTrue(players[0].getRow() == 0);
		assertTrue(players[0].getCol() == 6);
		
		assertTrue(players[1].getRow() == 19);
		assertTrue(players[1].getCol() == 0);
		
		assertTrue(players[2].getRow() == 27);
		assertTrue(players[2].getCol() == 7);
		
		assertTrue(players[3].getRow() == 27);
		assertTrue(players[3].getCol() == 18);
		
		assertTrue(players[4].getRow() == 23);
		assertTrue(players[4].getCol() == 24);
		
		assertTrue(players[5].getRow() == 0);
		assertTrue(players[5].getCol() == 20);
		
		
		assertTrue(players[0].getClass() == HumanPlayer.class);
		assertTrue(players[3].getClass() == ComputerPlayer.class);
	}
	@Test
	public void weaponSetup() {
		Stack<Card> deck = board.getDeck();
		
		Card needle = new Card(CardType.WEAPON, "Needle");
		Card spoon = new Card(CardType.WEAPON, "Spoon");
		Card football = new Card(CardType.WEAPON, "Football");
		Card book = new Card(CardType.WEAPON, "Book");
		Card chain = new Card(CardType.WEAPON, "Chain");
		Card glove = new Card(CardType.WEAPON, "Glove");

		assertTrue(deck.contains(needle));
		assertTrue(deck.contains(spoon));
		assertTrue(deck.contains(book));
		assertTrue(deck.contains(football));
		assertTrue(deck.contains(chain));
		assertTrue(deck.contains(glove));
		

	}
	
}
