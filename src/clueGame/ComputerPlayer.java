package clueGame;

public class ComputerPlayer extends Player{

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}
	
	public Solution createSuggestion() {
		return new Solution(null, null, null);		//PLACEHOLDER
	}
	
	public BoardCell selectTarget() {
		return new BoardCell();			//PLACEHOLDER
	}
}
