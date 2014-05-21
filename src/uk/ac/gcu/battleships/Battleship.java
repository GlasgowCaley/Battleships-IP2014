package uk.ac.gcu.battleships;

/**
 * A Battleship is a Ship.
 * his length is 4.
 * @author Yann Prono.
 */
public class Battleship extends Ship {
	
	/**
	 * Only constructor for a Battleship.
	 * @param position_x Coordinate for the X axis. 
	 * @param position_y Coordinate for the Y axis.
	 * @param orientation Orientation wanted.
	 */
	public Battleship(int position_x, int position_y, char orientation) {
		super(4,position_x,position_y,orientation);
		this.character = 'B';	
	}

}
