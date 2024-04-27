/*
 * Solution: Data type that players generate for suggestions/accusations
 * Authors: Mathew Grossman, Julian Reyes.
 */
package clueGame;

public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	
	public Solution(Card room, Card person, Card weapon) {
		super();
		this.room = room;
		this.person = person;
		this.weapon = weapon;
	}

	public Card getRoom() {
		return room;
	}
	
	public Card getPerson() {
		return person;
	}

	public Card getWeapon() {
		return weapon;
	}
	
	public boolean contains(Card c) {
		return (c.equals(room) || c.equals(person) || c.equals(weapon));
	}
	

	@Override
	public String toString() {
		return room.getCardName() + ", " + person.getCardName() + ", " + weapon.getCardName();
	}

}


