package uk.ac.gcu.battleships;
/**
 * A Submarine is a Ship.
 * This length is 
 * @author Yann Prono
 */
public class Submarine extends Ship {
	
	/**
	 * Only constructor for a Submarine.
	 * @param position_x Coordinate for the X axis. 
	 * @param position_y Coordinate for the Y axis.
	 * @param orientation Orientation wanted.
	 */
	public Submarine(int position_x, int position_y, char orientation) {
		super(3,position_x,position_y,orientation);
		this.character = 'S';		
	}

}
