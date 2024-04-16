package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.*;


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
		Player[] playerList = board.getPlayers();
		ComputerPlayer compPlayer = (ComputerPlayer) playerList[1];
		ArrayList <Card> weapons  = board.getWeaponCards();
		for(int i=1; i < weapons.size(); i++) {
			compPlayer.updateSeen(weapons.get(i));
		}
		Room occupied = board.getRoom('M');
		Solution compSol = compPlayer.createSuggestion(occupied);

		assertTrue(compSol.getWeapon()== weapons.get(0));

		//		One person unseen
		ArrayList <Card> people  = board.getPeopleCards();
		for(int i=1; i < people.size(); i++) {
			compPlayer.updateSeen(people.get(i));
		}
		occupied = board.getRoom('S');
		compSol = compPlayer.createSuggestion(occupied);

		assertTrue(compSol.getPerson()== people.get(0));

		//		Multiple weapons unseen
		compPlayer = (ComputerPlayer) playerList[2];
		for(int i=3; i < weapons.size(); i++) {
			compPlayer.updateSeen(weapons.get(i));
		}
		for(int i = 0; i < 10;i++) {
			compSol = compPlayer.createSuggestion(occupied);
			if (compPlayer.getSeenCards().contains(compSol.getWeapon())) {
				fail();
			}
		}	

		//		Multiple people unseen
		for(int i=3; i < people.size(); i++) {
			compPlayer.updateSeen(people.get(i));
		}
		for(int i = 0; i < 10;i++) {
			compSol = compPlayer.createSuggestion(occupied);
			if (compPlayer.getSeenCards().contains(compSol.getPerson())) {
				fail();
			}
		}	
	}

	@Test
	public void selectTarget() {
		Player[] players = board.getPlayers();
		ComputerPlayer cpu = (ComputerPlayer) players[1];
		//		No rooms
		cpu.setRow(8);
		cpu.setCol(21);
		board.calcTargets(board.getCell(8, 21), 2);
		Set<BoardCell> targets = board.getTargets();
		Set<BoardCell> seenTargets = new HashSet<BoardCell>();
		for(int i = 0; i < 100; i++) {
			cpu.setRow(8);
			cpu.setCol(21);
			BoardCell target = cpu.selectTarget(targets);
			seenTargets.add(target);

		}
		if(!seenTargets.equals(targets)) {
			fail("not every occurrence");
		}
		//		Room unseen
		cpu.setRow(8);
		cpu.setCol(15);
		board.calcTargets(board.getCell(8, 15), 3);
		targets = board.getTargets();
		for(int i = 0; i < 1; i++) {
			BoardCell target = cpu.selectTarget(targets);

			if(target == board.getRoom('T').getCenterCell()) {
				continue;
			}
			else fail("Not targetting unseen room");
		}
		
		
		//		Room seen
		Card room = board.getCard("Lecture Hall");
		cpu.updateSeen(room);
		seenTargets.clear();
		
		
		for(int i = 0; i < 100; i++) {
			BoardCell target = cpu.selectTarget(targets);
			seenTargets.add(target);

		}
		if(!seenTargets.equals(targets)) {
			fail("not every occurrence");
		
		}
	}


}
