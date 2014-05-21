package uk.ac.gcu.battleships.view;

import java.util.Observable;

import uk.ac.gcu.battleships.*;

/**
 * Class which enable us to control the game
 * @author Anthony Cerf 
 * @author Yann Prono
 */


public class GameController extends Observable{

	/** attribut which instantiate the game*/
	private Game game ;

	/** attribut which enable to create a link with InputView */
	private InputView iv ;

	
	/**
	 * Constructor which lauch the game
	 */
	public GameController(){
		game = new Game();
	}

	/**
	 * method which enables to change the message
	 * @param s = the message
	 */
	public void setMessage( String s ){
		this.notifyObservers(s);
	}
	/**
	 * Method which initialize the beginning of the Game
	 */
	public void initGame(){
		//input the name
		for ( int i = 0 ; i < game.getPlayer().length; i++){
			game.getPlayer()[i] = new Player(iv.getContent());
		}

		// place the ship
	

	}
}