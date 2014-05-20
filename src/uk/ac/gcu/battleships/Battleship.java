package uk.ac.gcu.battleships;

/**
 * A Battleship is a Ship.
 * his length is 4.
 * @author Yann Prono.
 */
public class Battleship extends Ship {
	
	public Battleship(int position_x, int position_y, char orientation) {
		super(4,position_x,position_y,orientation);
		this.character = 'B';		
	}

}
