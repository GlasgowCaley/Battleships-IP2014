package uk.ac.gcu.battleships.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
	 * Constructor for class GameController 
	 */
	public GameController () {
		
	}

	/**
	 * method which enables to change the message
	 * @param s = the message
	 */
	public void setMessage(String s){
		this.setChanged();
		this.notifyObservers(s);
	}
	/**
	 * Method which initialize the beginning of the Game
	 */
	public void initGame(){
		//input the name
		for ( int i = 0 ; i < game.getPlayer().length; i++){
			game.getPlayer()[i] = new Player(iv.getContent());}
		}

		// place the ship
	
	@FXML
	private static void btnClick () {
		String valueProperty = "!"; 								// Set the value to an exclamation mark
		Button btnTest = new Button() ;								// Create a new button called btnTest
		
		Label playerA1 = new Label();								// Create a label called playerA1
		
		btnTest.setOnAction(new EventHandler<ActionEvent>() {		// When a button is clicked make a new event
			
			@Override
			public void handle(ActionEvent event) {
		        playerA1.setText(valueProperty);
			}
		});
	}
}
