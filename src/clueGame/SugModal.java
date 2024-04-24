package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SugModal extends JDialog{
	Board board = Board.getInstance();
	Room room;

	JButton submitButton = new JButton("Submit");
	JButton cancelButton = new JButton("Cancel");

	public SugModal() {
		super();
		room = null;
		setTitle("Create an Accusation");
		create();
	}

	public SugModal(Room room) {
		super();
		this.room = room;
		setTitle("Create a suggestion");
		create();
	}

	private void create() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(new GridLayout(4,2));
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(1,2));

		JLabel roomLabel = new JLabel("Room: ");

		roomPanel.add(roomLabel);
		JComboBox<Card> rooms = new JComboBox<Card>();
		if(room != null) {
			rooms.addItem(board.getCard(room.getName()));
		}
		else {
			for(Card c : board.getRoomCards()) {
				rooms.addItem(c);
			}
		}
		roomPanel.add(rooms);

		JPanel weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(1,2));
		JLabel weaponLabel = new JLabel("Weapon: ");

		JComboBox<Card> weapons = new JComboBox<Card>();

		for(Card c : board.getWeaponCards()) {
			weapons.addItem(c);
		}

		weaponPanel.add(weaponLabel);
		weaponPanel.add(weapons);


		JPanel personPanel = new JPanel();
		personPanel.setLayout(new GridLayout(1,2));
		JLabel personLabel = new JLabel("Person: ");

		JComboBox<Card> people = new JComboBox<Card>();

		for(Card c : board.getPeopleCards()) {
			people.addItem(c);
		}

		personPanel.add(personLabel);
		personPanel.add(people);

		add(roomPanel);
		add(weaponPanel);
		add(personPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Solution suggestion = new Solution(((Card)rooms.getSelectedItem()),((Card)people.getSelectedItem()),((Card)weapons.getSelectedItem()));
				if(room == null) {
					board.checkAccusation(suggestion);
				}
				else {
					board.humanSuggestion(suggestion);
				}
				SugModal.this.dispose();
			}


		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(room != null) {
					board.humanSuggestion(null);
				}
				SugModal.this.dispose();
				board.endHumanTurn();
			}

		});
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel);

		setSize(300,200);
		setVisible(true);
	}

	public void close() {
		this.dispose();
	}
	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		SugModal modal = new SugModal();
		modal.setSize(300,200);
		modal.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		modal.setVisible(true);
	}
}
