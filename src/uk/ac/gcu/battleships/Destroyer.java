package uk.ac.gcu.battleships;
/**
 * A Destroyer is a Ship of length 2. 
 * @author Yann Prono
 */
public class Destroyer extends Ship {
	
	/**
	 * Only constructor for a Destroyer.
	 * @param position_x Coordinate for the X axis. 
	 * @param position_y Coordinate for the Y axis.
	 * @param orientation Orientation wanted.
	 */
	public Destroyer(int position_x, int position_y, char orientation) {
		super(2,position_x,position_y,orientation);
		this.character = 'D';		
	}

}
