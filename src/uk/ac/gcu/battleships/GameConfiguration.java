package uk.ac.gcu.battleships;

public class GameConfiguration {

	/**
	 * Static attribut which represents the grid size
	 */
	public static int gameConfiguration_SIZE = 5;
	
	/**
	 * Static attribut which represents the number of ship to be placed
	 */
	
	public static int gameConfiguration_NBSHIP = 3 ;

	/**
	 * Static attribut which represents the type oh the ship to be placed
	 */
	
	//public static ;
	
	/**
	 * Setters of the attributs which represents the size of the Grid
	 * @param size
	 */
	public void set_GameConfiguration_size(int size){
		GameConfiguration.gameConfiguration_SIZE = size ;
	}
	
	public void set_GameConfiguration_nbShip( int nb){
		GameConfiguration.gameConfiguration_NBSHIP = nb;
	}
	


}
