package clueGame;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GameControlPanel extends JPanel {
	
	private GameControlPanel gui;
	private JTextField playerNameTF = new JTextField();
	private JTextField guessTF = new JTextField();
	private JTextField guessResTF = new JTextField();
	private JTextField rollTF = new JTextField();



	public GameControlPanel()  {
		this.setSize(2,0);
	
		//Create Top Panel
		JPanel topPanel = new JPanel();
		topPanel.setSize(1,4);

		//Creates Name Panel
		JPanel turnPanel =  new JPanel();
		turnPanel.setSize(1,2);
		JLabel turnLabel = new JLabel("Who's Turn?");
		turnPanel.add(turnLabel);
		this.setPlayerNameTF("Player Name Here");
		turnPanel.add(this.getPlayerNameTF());
		
		//Creates Roll Panel
		JPanel rollPanel =  new JPanel();
		turnPanel.setSize(1,2);
		JLabel rollLabel = new JLabel("Roll:");
		rollPanel.add(rollLabel);
		this.setRollTF(0);
		rollPanel.add(this.getRollTF());
		
		//Creates Buttons
		JButton accuButton = new JButton("Make Accusation");
		JButton nextButton = new JButton("Next!");
		
		
		//Adds Elements to Top Panel
		topPanel.add(turnPanel);
		topPanel.add(rollPanel);
		topPanel.add(accuButton);
		topPanel.add(nextButton);
		
		//Create Bottom Panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setSize(0,2);
		
		//Creates Guess Panel
		JPanel guessPanel =  new JPanel();
		guessPanel.setSize(1,0);
		JLabel guessLabel = new JLabel("Guess:");
		guessPanel.add(guessLabel);
		this.setGuessTF("");
		guessPanel.add(this.guessTF);
		
		//Creates Guess Res Panel
		JPanel guessResPanel =  new JPanel();
		guessResPanel.setSize(1,0);
		JLabel guessResLabel = new JLabel("Guess Result:");
		guessResPanel.add(guessResLabel);
		this.setGuessResTF("");
		guessResPanel.add(this.guessResTF);
		
		//Adds Elements to Bottom Panel
		bottomPanel.add(guessPanel);
		bottomPanel.add(guessResPanel);

		
		//Adds Panels to Overall Panel
		this.add(topPanel);
		this.add(bottomPanel);
		
	}
	
	
	public JTextField getPlayerNameTF() {
		return playerNameTF;
	}


	public void setPlayerNameTF(String Name) {
		this.playerNameTF.setText(Name);
	}


	public JTextField getGuessTF() {
		return guessTF;
	}


	public void setGuessTF(String guess) {
		this.guessTF.setText(guess);
	}

	public JTextField getGuessResTF() {
		return guessResTF;
	}

	public void setGuessResTF (String guessRes) {
		this.guessResTF.setText(guessRes);
	}
	
	public JTextField getRollTF() {
		return rollTF;
	}

	public void setRollTF(Integer val) {
		String roll = val.toString();
		this.rollTF.setText(roll);
	}

	private void setTurn(ComputerPlayer computerPlayer, Integer val) {
		this.setPlayerNameTF(computerPlayer.getName());
		this.setRollTF(val);
	}
	
	
	
	/**
	 * Main to test the panel
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame("Clue Control GUI");  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
		
		//test filling in the data
		panel.setTurn(new ComputerPlayer( "Col. Mustard", "orange", 0, 0), 5);
		panel.setGuessTF( "I have no guess!");
		panel.setGuessResTF( "So you have nothing?");
	}



}
