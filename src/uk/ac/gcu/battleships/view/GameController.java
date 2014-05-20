package uk.ac.gcu.battleships.view;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import uk.ac.gcu.battleships.*;

/** 
 * This controller method drives Game.fxml
 * Work in progress
 * @author Robert
 * @version 0.1
 */
public class GameController extends AnchorPane {
	
	@FXML
	Button btnTest;
	
	@FXML
	Label playerA1;
	
	/**
	 * Reference back to Game class
	 */
	private Game game;
	
	/** 
	 * Constructor for class GameController 
	 */
	public GameController () {
		
	}
	
	@FXML
	private static void gridManager () {

	}
	
	@FXML
	private static void btnClick () {
		String valueProperty = "!"; 								// Set the value to an exclamation mark
		Button btnTest = new Button() ;								// Create a new button called btnTest
		
		Label playerA1 = new Label();								// Create a label called playerA1
		
		btnTest.setOnAction(new EventHandler<ActionEvent>() {		// When a button is clicked make a new event
			
			@Override
			public void handle(ActionEvent event) {
		        playerA1.setText("a");
			}
		});
	}
}

