package uk.ac.gcu.battleships.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.gcu.battleships.Game;


/**
 * Class which displays the Game !
 * @author Anthony Cerf
 *
 */
public class Panel extends JPanel{
	
	private GameView game;
	
	public Panel (){
		Game g = new Game();
		g.initPlayer();
		this.game =new GameView(g);
		JLabel texte = new JLabel ("BATTLESHIPS",JLabel.CENTER);
		texte.setFont(new Font("Stencil",1,50));
		this.add (texte, BorderLayout.NORTH);
		this.add(game,BorderLayout.CENTER);
	}
	
	
	public static void main ( String [] args){
		JFrame fenetre = new JFrame();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel dessin = new Panel();
	
		dessin.setPreferredSize(new Dimension(1024,720));
		fenetre.setContentPane(dessin);
		fenetre.pack();
		fenetre.setVisible(true);
	}
}
