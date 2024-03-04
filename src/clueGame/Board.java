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
		grid = new BoardCell[numRows][numCols];
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		roomMap = new HashMap<Character, Room>();
		this.loadSetupConfig();
		this.loadLayoutConfig();

		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		//		Generate adjacency list

		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				BoardCell cell = this.getCell(row, col);

				//				Test for each edge. If not on an edge, there is an adjacency in that direction
				if(row > 0) {
					cell.addAdjacency(this.getCell(row - 1, col));
				}
				if(row + 1 < numRows) {
					cell.addAdjacency(this.getCell(row + 1, col));
				}

				if(col > 0) {
					cell.addAdjacency(this.getCell(row, col - 1));
				}
				if(col + 1 < numRows) {
					cell.addAdjacency(this.getCell(row, col + 1));
				}

			}
		}
	}

	public void loadSetupConfig() {
		try {
			FileReader reader = new FileReader(setupConfigFile);
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
		BoardCell cell = new BoardCell(0, 0);
		return cell;	
	}

	/** 
	 *   getTargets: gets the targets last created by calcTargets().
	 * 
	 */
	public Room getRoom(BoardCell cell) {
		// TODO Auto-generated method stub
		Room room = new Room();
		return room;
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
