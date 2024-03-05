/*
 * Authors: Mathew Grossman, Julian Reyes
 */
package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;

import clueGame.BoardCell;


public class Board {
	private BoardCell[][] grid;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;

	private String layoutConfigFile;
	private String setupConfigFile;

	private int numCols;
	private int numRows;

	private Map<Character, Room> roomMap;

	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board Instance = new Board();

	private Board() {
		super();
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		roomMap = new HashMap<Character, Room>();
	}
	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize()  {

		try {
			this.loadSetupConfig();
			this.loadLayoutConfig();
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}

	}

	/*
	 * loadSetupConfig: Loads in expected rooms from setup.txt file
	 */
	public void loadSetupConfig() throws BadConfigFormatException, FileNotFoundException {
		FileReader reader = new FileReader("data/" + setupConfigFile);
		Scanner in  = new Scanner(reader);

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


		FileReader reader = new FileReader("data/" + layoutConfigFile);
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

		FileReader read2 = new FileReader("data/" + layoutConfigFile);
		Scanner in = new Scanner(read2);

		while(in.hasNextLine()) {
			col = 0;
			String rowStr = in.nextLine();
			String[] rowArr = rowStr.split(",");

			for(String c : rowArr) {
				c = c.trim();
				char roomLabel = c.charAt(0);
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

					}

					if(roomMap.containsKey(special)) {
						cell.setSecretPassage(special);
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
	public static Board getInstance() {
		return Instance;
	}
	/** 
	 * calcTargets: calculates legal targets for a move from startCell of length pathlength.
	 * 
	 */

	public void calcTargets( BoardCell startCell, int pathlength) {
		return;
	}

	/** 
	 * targetCalc: calculates legal targets for a move from startCell of length pathlength.
	 * 
	 */
	private void targetCalc(BoardCell startCell, int pathlength) {
		return;
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
		Set <BoardCell> adjList = grid[row][col].getAdjList();
		return adjList;
	}


	
}
