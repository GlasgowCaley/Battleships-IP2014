package uk.ac.gcu.battleships.view;
import java.awt.Dimension;
import javax.swing.JFrame;
import uk.ac.gcu.battleships.Game;

public class BattleshipsGUI {
	
	public static void main ( String [] args){
		JFrame fenetre = new JFrame();		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setMinimumSize(new Dimension(400,300));
		fenetre.setSize(new Dimension(1100,700));
		Game g = new Game();
		g.initPlayer();				
		Panel dessin = new Panel(g);
		fenetre.setContentPane(dessin);
		//fenetre.pack();
		fenetre.setVisible(true);
		dessin.requestFocus();
	}
}
