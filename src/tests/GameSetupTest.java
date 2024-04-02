package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;
public class GameSetupTest {
	private static Board board;
	
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
	public void deckSetup() {
		ArrayList<Card> deck = board.getDeck();
		
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
		

//		Test Rooms
		Card classroom = new Card(CardType.ROOM, "Classroom");
		Card office = new Card(CardType.ROOM, "Office");
		Card labratory = new Card(CardType.ROOM, "Labratory");
		Card storage = new Card(CardType.ROOM, "Storage");
		Card machineShop = new Card(CardType.ROOM, "Machine Shop");
		Card computerLab = new Card(CardType.ROOM, "Computer Lab");
		Card coffeeShop = new Card(CardType.ROOM, "Coffee Shop");
		Card lectureHall = new Card(CardType.ROOM, "Lecture Hall");
		Card gameRoom = new Card(CardType.ROOM, "Game Room");
		
		assertTrue(deck.contains(classroom));
		assertTrue(deck.contains(office));
		assertTrue(deck.contains(labratory));
		assertTrue(deck.contains(storage));
		assertTrue(deck.contains(machineShop));
		assertTrue(deck.contains(computerLab));
		assertTrue(deck.contains(coffeeShop));
		assertTrue(deck.contains(lectureHall));
		assertTrue(deck.contains(gameRoom));
		
		
		Card dray = new Card(CardType.PERSON, "Draymond Green");
		Card patty = new Card(CardType.PERSON, "Patrick Mahomes");
		Card jj = new Card(CardType.PERSON, "Justin Jefferson");
		Card allen = new Card(CardType.PERSON, "Josh Allen");
		Card brr = new Card(CardType.PERSON, "Joe Brrr");
		Card letsRide = new Card(CardType.PERSON, "Russell Wilson");
		
		assertTrue(deck.contains(dray));
		assertTrue(deck.contains(patty));
		assertTrue(deck.contains(jj));
		assertTrue(deck.contains(allen));
		assertTrue(deck.contains(brr));
		assertTrue(deck.contains(letsRide));


	}
	@Test
	public void dealCards() {
		ArrayList<Card> deck = board.getDeck();
		board.deal();
		Player[] players = board.getPlayers();
		Solution answer = board.getTheAnswer();
		
		assertTrue(answer.getPerson().getCardType() == CardType.PERSON);
		assertTrue(answer.getRoom().getCardType() == CardType.ROOM);
		assertTrue(answer.getWeapon().getCardType() == CardType.WEAPON);
		
		
		assertTrue(players[0].getHand().size()== 3);
		assertTrue(players[1].getHand().size() == 3);
		assertTrue(players[2].getHand().size() == 3);
		assertTrue(players[3].getHand().size() == 3);
		assertTrue(players[4].getHand().size() == 3);
		assertTrue(players[5].getHand().size() == 3);
		
	}
	

}
