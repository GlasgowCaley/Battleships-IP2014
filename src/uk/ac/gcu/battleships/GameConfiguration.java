package uk.ac.gcu.battleships;

/**
 * Class which enable to configurate the game
 * 
 *
 */
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
	 * Static attribut which represents the id of the Destroyer
	 */
	
	public static int id_Destroyer = 01 ;


	/**
	 * Static attribut which represents the id of the Submarine
	 */
	
	public static int id_Submarine = 02 ;

	/**
	 * Static attribut which represents the id of the Battleship
	 */
	
	public static int id_Battleship = 03 ;

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
