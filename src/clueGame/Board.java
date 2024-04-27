/*
 * Board: Holds information about game board and does most turn-to-turn logic
 * Authors: Mathew Grossman, Julian Reyes
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Board {
	private BoardCell[][] grid;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;

	private String layoutConfigFile;
	private String setupConfigFile;

	private ClueGame clueGameDisplay;


	private int numCols;
	private int numRows;
	private int curPlayerIDX = 0;
	private boolean isHumanTurn;
	private int roll;
	private boolean sugProven;

	private Solution prevSug;

	private boolean panelOpen = false;

	private Map<Character, Room> roomMap;
	private Solution theAnswer;

	private Player[] players = new Player[6];
	private Player curPlayer;
	private ArrayList<Card> deck;	

	private Random rng = new Random();
	final static String dataFolder = "data/";

	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board Instance = new Board();

	private Board() {
		super();
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		roomMap = new HashMap<Character, Room>();
		deck = new ArrayList<Card>();
	}
	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize()  {
		this.clear();
		try {
			this.loadSetupConfig();
			this.loadLayoutConfig();
			this.calcAdj();
			clueGameDisplay = new ClueGame();
			isHumanTurn = true;
			curPlayer = players[0];
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	/*
	 * loadSetupConfig: Loads in expected rooms from setup.txt file
	 */
	public void loadSetupConfig() throws BadConfigFormatException, FileNotFoundException {

		this.clear(); //clears roomMap and deck

		FileReader reader = new FileReader(dataFolder + setupConfigFile);
		Scanner in  = new Scanner(reader);
		int playerCtr = 0;

		while(in.hasNextLine()) {

			String nextLine = in.nextLine();
			if(nextLine.contains("//") || nextLine.isBlank()) {
				continue;
			}

			String[] roomInfo = nextLine.split(",");

			if (roomInfo[0].equals("Room") || roomInfo[0].equals("Space")) {
				Room r = new Room();
				r.setName(roomInfo[1].trim());
				char label = roomInfo[2].trim().charAt(0);
				roomMap.put(label, r);

				//				Create card
				if(roomInfo[0].equals("Room")) {
					deck.add(new Card(CardType.ROOM, roomInfo[1].trim()));
				}
			}
			else if(roomInfo[0].equals("Player")){
				String name = roomInfo[1].trim();
				String color = roomInfo[2].trim();
				int row = Integer.parseInt(roomInfo[3].trim());
				int col = Integer.parseInt(roomInfo[4].trim());

				if(playerCtr == 0) {
					HumanPlayer human = new HumanPlayer(name, color, row, col);
					players[playerCtr] = human;
				}
				else {
					ComputerPlayer cpu = new ComputerPlayer(name, color, row, col);
					players[playerCtr] = cpu;
				}

				deck.add(new Card(CardType.PERSON, name));
				playerCtr++;

			}
			else if(roomInfo[0].equals("Weapon")) {
				String name = roomInfo[1].trim();
				deck.add(new Card(CardType.WEAPON, name));
			}
			else {
				throw new BadConfigFormatException("Formatting Error in " + setupConfigFile);
			}

		}
		return;
	}

	/*
	 * loadLayoutConfig: Loads layout csv file and checks against setup file
	 */
	public void loadLayoutConfig() throws BadConfigFormatException, FileNotFoundException{
		//			Get rows, cols values
		FileReader reader = new FileReader(dataFolder + layoutConfigFile);
		Scanner rowCount = new Scanner(reader);
		int row = 0;
		int col = 0;
		while(rowCount.hasNextLine()) {
			String temp = rowCount.nextLine();
			String[] tempArr = temp.split(",");
			numCols = tempArr.length;
			row++;
		}
		rowCount.close();
		numRows = row;
		row = 0;
		col = 0;
		grid = new BoardCell[numRows][numCols];

		FileReader read2 = new FileReader(dataFolder + layoutConfigFile);
		Scanner in = new Scanner(read2);

		while(in.hasNextLine()) {
			col = 0;
			String rowStr = in.nextLine();
			String[] rowArr = rowStr.split(",");

			for(String c : rowArr) {
				c = c.trim();
				char roomLabel = Character.toUpperCase(c.charAt(0));
				BoardCell cell = new BoardCell(row,col);

				if(roomMap.containsKey(roomLabel)){
					cell.setInitial(roomLabel);
				}
				else {
					throw new BadConfigFormatException("Room type '" + roomLabel + "' not found in " + setupConfigFile);
				}
				if(c.length() > 1) {
					char special = c.charAt(1);
					switch(special) {
					case '#': //label
						cell.setRoomLabel(true);
						roomMap.get(cell.getInitial()).setLabelCell(cell);
						break;
					case '*': //center
						cell.setRoomCenter(true);
						roomMap.get(cell.getInitial()).setCenterCell(cell);
						break;
					case '^':
						cell.setDoorDirection(DoorDirection.UP);
						break;
					case '>':
						cell.setDoorDirection(DoorDirection.RIGHT);
						break;
					case 'v':
						cell.setDoorDirection(DoorDirection.DOWN);
						break;
					case '<':
						cell.setDoorDirection(DoorDirection.LEFT);
						break;
					default:
						if(roomMap.containsKey(special)) {
							cell.setSecretPassage(special);
						}
						else {
							throw new BadConfigFormatException("Unexcpected Character " + special + " in " + layoutConfigFile);
						}
						break;
					}

				}

				grid[row][col] = cell;
				col++;
			}

			if(col != numCols) {
				throw new BadConfigFormatException("Error: Column count not consistent. Double Check " + layoutConfigFile);
			}
			row++;
		}
		return;
	}


	/*
	 * calcAdj: Creates a list of all the cells that are directly next to a cell
	 */
	public void calcAdj() {

		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {

				BoardCell cell = this.getCell(row, col);

				//				If its not a room center or a walkway, it doesn't have adjacency
				if(!cell.isRoomCenter() && cell.getInitial() != 'W') {
					if(roomMap.containsKey(cell.getSecretPassage())){
						BoardCell roomCenter1 = roomMap.get(cell.getInitial()).getCenterCell();
						BoardCell roomCenter2 = roomMap.get(cell.getSecretPassage()).getCenterCell();
						roomCenter1.addAdjacency(roomCenter2);
						roomCenter2.addAdjacency(roomCenter1);
					}
					continue;
				}

				if(cell.getDoorDirection() != DoorDirection.NONE) {
					BoardCell roomCell;
					Room doorRoom;
					BoardCell centerCell;

					switch(cell.getDoorDirection()) {
					case UP:
						roomCell = this.getCell(row - 1, col);
						doorRoom = roomMap.get(roomCell.getInitial());
						centerCell = doorRoom.getCenterCell();

						cell.addAdjacency(centerCell);
						centerCell.addAdjacency(cell);
						break;
					case DOWN:
						roomCell = this.getCell(row + 1, col);
						doorRoom = roomMap.get(roomCell.getInitial());
						centerCell = doorRoom.getCenterCell();

						cell.addAdjacency(centerCell);
						centerCell.addAdjacency(cell);
						break;
					case LEFT:
						roomCell = this.getCell(row, col - 1);
						doorRoom = roomMap.get(roomCell.getInitial());
						centerCell = doorRoom.getCenterCell();

						cell.addAdjacency(centerCell);
						centerCell.addAdjacency(cell);
						break;
					case RIGHT:
						roomCell = this.getCell(row, col + 1);
						doorRoom = roomMap.get(roomCell.getInitial());
						centerCell = doorRoom.getCenterCell();

						cell.addAdjacency(centerCell);
						centerCell.addAdjacency(cell);
						break;
					default:
						break;
					}


				}

				if(row > 0) {
					BoardCell closeCell = this.getCell(row - 1, col);
					if(closeCell.getInitial() == 'W') {
						cell.addAdjacency(closeCell);
					}
				}
				if(row + 1 < numRows) {
					BoardCell closeCell = this.getCell(row + 1, col);
					if(closeCell.getInitial() == 'W') {
						cell.addAdjacency(closeCell);
					}				
				}

				if(col > 0) {
					BoardCell closeCell = this.getCell(row, col - 1);
					if(closeCell.getInitial() == 'W') {
						cell.addAdjacency(closeCell);
					}				}
				if(col + 1 < numCols) {
					BoardCell closeCell = this.getCell(row, col + 1);
					if(closeCell.getInitial() == 'W') {
						cell.addAdjacency(closeCell);
					}				
				}

			}
		}
		return;
	}

	/** 
	 * calcTargets: calculates legal targets for a move from startCell of length pathlength.
	 * 
	 */

	public void calcTargets(BoardCell startCell, int pathlength) {
		targets.clear();
		targetCalc(startCell, pathlength);
		if (curPlayer.isWasAccused()) {
			targets.add(startCell);
		}
		curPlayer.setWasAccused(false);
	}

	/** 
	 * targetCalc: helper function of calcTargets that identifies all the targets that a player can move to
	 * 
	 */
	private void targetCalc(BoardCell startCell, int pathlength) {
		if (pathlength == 0) {
			targets.add(startCell);
			return;
		}

		visited.add(startCell); 

		for (BoardCell cell : startCell.getAdjList()) {	
			if (!visited.contains(cell) && !cell.isOccupied()) {
				if (cell.getInitial() != 'W') {
					targets.add(cell);
				} 
				else {
					targetCalc(cell, pathlength - 1);
				}
			}
			if (!visited.contains(cell) && cell.isOccupied() && cell.isRoomCenter()) {
				targets.add(cell);
			}
		}

		visited.remove(startCell);
	}

	/** 
	 * deal: function that shuffles deck to solution and to players
	 */

	public void deal(){
		Collections.shuffle(deck);
		dealSolution();
		dealPlayers();
		Collections.shuffle(deck);
	}

	/** 
	 * dealSolution: helper function of deal() that deals a room card, person card, and weapon card to solution
	 */

	public void dealSolution() {
		Card roomCard = null;
		Card personCard = null;
		Card weaponCard = null;

		int cardCount = 0;
		while (roomCard == null || personCard == null || weaponCard == null) {
			if(roomCard == null && deck.get(cardCount).getCardType().equals(CardType.ROOM)) {
				roomCard = deck.get(cardCount);
				cardCount = 0;
			}
			else if(personCard == null && deck.get(cardCount).getCardType().equals(CardType.PERSON)) {
				personCard = deck.get(cardCount);
				cardCount = 0;
			}
			else if(weaponCard == null && deck.get(cardCount).getCardType().equals(CardType.WEAPON)) {
				weaponCard = deck.get(cardCount);
				cardCount = 0;
			}
			else {
				cardCount ++;
			}
		}
		theAnswer = new Solution(roomCard,personCard,weaponCard);
		return;
	}

	/** 
	 * dealPlayer:  helper function of deal() that deals all remaining cards to the players 
	 */
	public void dealPlayers() {
		int playerCount = 0;
		for (Card c: deck) {
			if (theAnswer.contains(c)==true) {
				continue;
			}
			players[playerCount].updateHand(c);
			playerCount++;
			if (playerCount >= players.length) {
				playerCount = 0;
			}
		}
		return;
	}


	/*
	 * nextButton(): Event driven by next button pressed.
	 */
	public void nextButton() {
		if(isHumanTurn) {
			JOptionPane.showMessageDialog(null, "Please finish your turn!");
			return;
		}
		//		update current player
		curPlayerIDX++;
		curPlayerIDX = curPlayerIDX % 6;
		curPlayer = players[curPlayerIDX];
		if(curPlayer.getClass().equals(HumanPlayer.class)) {
			isHumanTurn = true;
		}
		clueGameDisplay.getControlPanel().updateFields();
		turn();
		//		If human: display targets. Flag turn unfinished
		//		end
		//		else: Computer check accusation, then move, then suggestion
	}

	/*
	 * turn(): Computer 
	 *
	 */
	public void turn() {
		//		Roll dice
		roll = rollDice();
		//		Calc targets
		calcTargets(getCell(curPlayer.getRow(), curPlayer.getCol()), roll);

		if (!isHumanTurn) {
			if (sugProven == true) {
				checkAccusation(prevSug);
			}
			((ComputerPlayer)curPlayer).selectTarget(targets);
			BoardCell cur = grid[curPlayer.getRow()][curPlayer.getCol()];
			if (cur.isRoomCenter()) {
				Solution sug = ((ComputerPlayer)curPlayer).createSuggestion(getRoom(cur));
				Player accused = getPlayer(sug.getPerson().getCardName());
				accused.setWasAccused(true);
				accused.setRow(cur.getRow());
				accused.setCol(cur.getCol());
				Room accuRoom = getRoom(cur.getInitial());
				accuRoom.addOccupant(accused);
				Card disproveCard  = handleSuggestion(sug,curPlayer);
				if (disproveCard == null) {
					sugProven = true;
					prevSug = sug;
				}

			}

		}
		clueGameDisplay.repaint();
		//		clueGameDisplay.update();

	}
	
	/*
	 * mouseClick(): Event process for mouse clicks on screen.
	 */
	public void mouseClick(int row, int col) {
		if (isHumanTurn) {
			BoardCell cell = getCell(row,col);
			if(targets.contains(cell)) {
				targets.clear();
				((HumanPlayer)curPlayer).updateOccupancy(cell);

				if(cell.isRoomCenter()) {
					Room r = getRoom(cell);
					SugModal window = new SugModal(r);
				}
				else {
					isHumanTurn = false;
				}
				clueGameDisplay.repaint();
			}
			else {
				JOptionPane.showMessageDialog(null, "Select a Proper Target");
			}
		}
	}

	/*
	 * humanSuggestion(): Processes suggestions made by player.
	 * Event-driven by GUI
	 */
	public void humanSuggestion(Solution suggestion) {
		if(suggestion == null)return;
		Player accused = getPlayer(suggestion.getPerson().getCardName());
		accused.setRow(curPlayer.getRow());
		accused.setCol(curPlayer.getCol());
		accused.setWasAccused(true);
		BoardCell cur = grid[curPlayer.getRow()][curPlayer.getCol()];
		Room accuRoom = getRoom(cur.getInitial());
		accuRoom.addOccupant(accused);
		Card disproveCard = handleSuggestion(suggestion, curPlayer);
		if (disproveCard == null) {
			sugProven = true;
			prevSug = suggestion;
		} 
		isHumanTurn = false;
		clueGameDisplay.repaint();
	}


	private int rollDice() {
		int roll = rng.nextInt(1, 7);
		clueGameDisplay.getControlPanel().setRollTF(roll);
		return roll;
	}
	/** 
	 * handleSuggestion: function that proves to handle a provided suggestion to the answer
	 */

	public Card handleSuggestion(Solution suggestion, Player suggester) {
		clueGameDisplay.getControlPanel().setGuessTF(suggestion.toString());
		int idx = 0;
		for(int i = 0; i < players.length; i++) {
			if(suggester == players[i]) {
				idx = (i + 1) % players.length;
			}
		}

		while(players[idx] != suggester) {
			Card disprove = players[idx].disproveSuggestion(suggestion);
			if(disprove == null) {
				idx++;
				idx = idx % players.length;
				continue;
			}
			else {
				suggester.updateSeen(disprove);
				if (isHumanTurn) {
					clueGameDisplay.getControlPanel().setGuessResTF(disprove.getCardName());
					clueGameDisplay.getCardPanel().updatePanels();
				}
				else {
					clueGameDisplay.getControlPanel().setGuessResTF("Guess Was Wrong");
				}
				return disprove;
			}

		}
		clueGameDisplay.getControlPanel().setGuessResTF("Guess Was Not Disproven");
		return null;		
	}

	/*
	 * checkAccusation: Checks accusation against answer
	 */
	public boolean checkAccusation(Solution accusation) {
		if(this.theAnswer.getPerson()==accusation.getPerson()&& this.theAnswer.getRoom()==accusation.getRoom()&& this.theAnswer.getWeapon()==accusation.getWeapon()) {
			return true;
		}
		else {
			return false;
		}
	}

	public static Board getInstance() {
		return Instance;
	}

	/** 
	 *  getCell: returns the cell from the board at row, col.
	 * 
	 */

	public BoardCell getCell(int row, int col) {
		return grid[row][col];	
	}

	/** 
	 *   getTargets: gets the targets last created by calcTargets().
	 * 
	 */
	public Room getRoom(BoardCell cell) {
		Room r = roomMap.get(cell.getInitial());
		return r;
	}
	public Room getRoom(char c) {
		return roomMap.get(c);
	}

	public Set<BoardCell> getTargets() {
		return targets;

	}
	public void setConfigFiles(String layoutConfig, String setupConfig) {
		layoutConfigFile = layoutConfig;
		setupConfigFile = setupConfig;
		return;
	}

	public int getNumColumns() {
		return numCols;
	}
	public int getNumRows() {
		return numRows;
	}

	public Set<BoardCell> getAdjList(int row, int col) {
		Set <BoardCell> adjList = this.getCell(row, col).getAdjList();
		return adjList;
	}

	public void setTheAnswer(Solution theAnswer) {
		this.theAnswer = theAnswer;
	}

	public ArrayList<Card> getWeaponCards(){
		ArrayList<Card> weaponCards = new ArrayList<Card>();
		for(Card card: deck) {
			if(card.getCardType()==CardType.WEAPON) {
				weaponCards.add(card);
			}
		}
		return weaponCards;
	}

	public ArrayList<Card> getRoomCards(){
		ArrayList<Card> roomCards = new ArrayList<Card>();
		for(Card card: deck) {
			if(card.getCardType()==CardType.ROOM) {
				roomCards.add(card);
			}
		}
		return roomCards;
	}

	public ArrayList<Card> getPeopleCards(){
		ArrayList<Card> peopleCards = new ArrayList<Card>();
		for(Card card: deck) {
			if(card.getCardType()==CardType.PERSON) {
				peopleCards.add(card);
			}
		}
		return peopleCards;
	}

	public Card getCard(String cardName) {
		for(Card c : deck) {
			if(c.getCardName().equals(cardName)) {
				return c;
			}
		}
		return null;
	}

	public Solution getTheAnswer() {
		return theAnswer;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public BoardCell[][] getGrid() {
		return grid;
	}
	public ArrayList<Room> getRooms(){
		return new ArrayList<Room>(roomMap.values());
	}
	private void clear() {
		roomMap.clear();
		deck.clear();
	}

	public static Color getColor(String color) {
		switch (color) {
		case "Green":
			return Color.GREEN;
		case "Red":
			return Color.RED;
		case "Purple":
			return Color.MAGENTA;
		case "Blue":
			return Color.BLUE;
		case "Orange":
			return Color.ORANGE;
		case "Yellow":
			return Color.YELLOW;
		default:
			return null;
		}
	}

	public Player[] getPlayers() {
		return players;
	}

	public Player getCurPlayer() {
		return players[curPlayerIDX];
	}

	public boolean isHumanTurn() {
		return isHumanTurn;
	}

	public int getRoll() {
		return roll;
	}
	public ClueGame getClueGameDisplay() {
		return clueGameDisplay;
	}

	public boolean isSugProven() {
		return sugProven;
	}
	public void setSugProven(boolean sugProven) {
		this.sugProven = sugProven;
	}
	public Player getPlayer(String name) {
		for (Player p: players) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public void endHumanTurn() {
		this.isHumanTurn = false;
	}

	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.deal();
		ClueGame game = board.getClueGameDisplay();
		game.setLayout(new BorderLayout());
		BoardPanel panel = game.getBoardpanel();
		GameControlPanel controlPanel = game.getControlPanel();
		CardPanel cardPanel = game.getCardPanel();
		cardPanel.updatePanels();
		controlPanel.updateFields();

		game.getContentPane().add(panel, BorderLayout.CENTER);
		game.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		game.getContentPane().add(cardPanel,BorderLayout.EAST);
		game.setSize(750, 750);  // size the frame
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		game.setVisible(true); // make it visible

		JOptionPane.showMessageDialog(null, "You are Draymond Green. Can you find the solution before the Computer Players?");
		controlPanel.setPlayerNameTF(board.getCurPlayer().getName());
		board.turn();
	}
}
