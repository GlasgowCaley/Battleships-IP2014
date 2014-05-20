package uk.ac.gcu.battleships;
/**
 * A Destroyer is a Ship of length 2. 
 * @author Yann Prono
 */
public class Destroyer extends Ship {
	
	/**
	 * Constructor of Destroyer.
	 */
	public Destroyer(int position_x, int position_y, char orientation) {
		super(2,position_x,position_y,orientation);
		this.character = 'D';		
	}

}
