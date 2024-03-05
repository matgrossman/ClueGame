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
	}
	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize() {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		roomMap = new HashMap<Character, Room>();
		this.loadSetupConfig();
		this.loadLayoutConfig();

	}

	public void loadSetupConfig() {
		try {
			FileReader reader = new FileReader("data/" + setupConfigFile);
			Scanner in  = new Scanner(reader);

			while(in.hasNextLine()) {
				String nextLine = in.nextLine();
				if(nextLine.contains("//") || nextLine.isBlank()) {
					continue;
				}

				String[] roomInfo = nextLine.split(",");

				Room r = new Room();
				r.setName(roomInfo[1].trim());
				char label = roomInfo[2].trim().charAt(0);
				roomMap.put(label, r);
			}
		}
		catch(Exception e){
			return;
		}
		return;
	}

	public void loadLayoutConfig() {
		try {
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
					System.out.println("Error");
				}
				row++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		targets.clear();
		targetCalc(startCell, pathlength);
		return;
	}

	/** 
	 * targetCalc: calculates legal targets for a move from startCell of length pathlength.
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
				if (cell.isRoom()) {
					targets.add(cell);
				} else {
					targetCalc(cell, pathlength - 1);
				}
			}
		}

		visited.remove(startCell);
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
		// TODO Auto-generated method stub
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


}
