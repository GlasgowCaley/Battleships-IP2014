package uk.ac.gcu.battleships.view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import uk.ac.gcu.battleships.*;

/**
 * Class which enable us to control the game
 * @author Anthony Cerf 
 * @author Yann Prono
 */

public class GameController extends MouseAdapter{

	private Game game = GameView.game;
	
	public GameController() {}
	
	public void mousePressed(MouseEvent e){		
		if(e.getSource() instanceof JLabel) {
			JLabel j = (JLabel)e.getSource();
			int x = Character.getNumericValue(j.getName().charAt(0));
			int y = Character.getNumericValue(j.getName().charAt(1));
			
			boolean ok = game.getPlayer()[game.current].opponentGrid.checkGuess(new Guess(x,y));
			if(!ok) {
				game.changePlayer();
			}
			else {
				
				
			}
			
		}	
		
	}
	
	
}