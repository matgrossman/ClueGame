package clueGame;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GameControlPanel extends JPanel {
	
	private JTextField playerNameTF = new JTextField();
	private JTextField guessTF = new JTextField();
	private JTextField guessResTF = new JTextField();
	private JTextField rollTF = new JTextField();
	private Board board = Board.getInstance();

	public GameControlPanel()  {
		this.setLayout(new GridLayout(2,0));
	
		
		playerNameTF.setEditable(false);
		guessResTF.setEditable(false);
		guessTF.setEditable(false);
		rollTF.setEditable(false);
		//Create Top Panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,4));

		//Creates Name Panel
		JPanel turnPanel =  new JPanel();
		turnPanel.setLayout(new GridLayout(1,2));
		JLabel turnLabel = new JLabel("Who's Turn?");
		turnPanel.add(turnLabel);
		this.setPlayerNameTF("Player Name Here");
		turnPanel.add(this.getPlayerNameTF());
		
		//Creates Roll Panel
		JPanel rollPanel =  new JPanel();
		turnPanel.setLayout(new GridLayout(1,2));
		JLabel rollLabel = new JLabel("Roll:");
		rollPanel.add(rollLabel);
		this.setRollTF(0);
		rollPanel.add(this.getRollTF());
		
		//Creates Buttons
		JButton accuButton = new JButton("Make Accusation");
		
		accuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!board.isHumanTurn()) {
					JOptionPane.showMessageDialog(null, "It is not your turn!");
				}
				else{
					new SugModal();
				}
			}
			
		});
		JButton nextButton = new JButton("Next!");
		
		nextButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				board.nextButton();
				
			}
			
		});
		
		//Adds Elements to Top Panel
		topPanel.add(turnPanel);
		topPanel.add(rollPanel);
		topPanel.add(accuButton);
		topPanel.add(nextButton);
		
		//Create Bottom Panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0,2));
		
		//Creates Guess Panel
		JPanel guessPanel =  new JPanel();
		guessPanel.setLayout(new GridLayout(2,0));
		JLabel guessLabel = new JLabel("Guess:");
		guessPanel.add(guessLabel);
		this.setGuessTF("No Guess");
		guessPanel.add(this.guessTF);
		
		//Creates Guess Res Panel
		JPanel guessResPanel =  new JPanel();
		guessResPanel.setLayout(new GridLayout(2,0));
		JLabel guessResLabel = new JLabel("Guess Result:");
		guessResPanel.add(guessResLabel);
		this.setGuessResTF("Who Knows?");
		guessResPanel.add(this.guessResTF);
		
		//Adds Elements to Bottom Panel
		bottomPanel.add(guessPanel);
		bottomPanel.add(guessResPanel);

		
		//Adds Panels to Overall Panel
		this.add(topPanel);
		this.add(bottomPanel);
		
	}
	
	public void updateFields() {
		setPlayerNameTF(board.getCurPlayer().getName());
		setRollTF(board.getRoll());
		setGuessTF("");
		setGuessResTF("");
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
