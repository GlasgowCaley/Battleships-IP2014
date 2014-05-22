package uk.ac.gcu.battleships.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import uk.ac.gcu.battleships.Game;


/**
 * Class which displays the Game !
 * @author Anthony Cerf
 *
 */
public class Panel extends JPanel {
	
	private GameView gameV;
	private Game game;
	
	
	public Panel (Game g){
		super();		
		this.game =g;
		this.gameV = new GameView(g);
		g.addObserver(this.gameV);
		this.init();
	}
	
	public void init() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());		
		JLabel texte = new JLabel ("BATTLESHIPS",JLabel.CENTER);
	    texte.setLayout(null);
		texte.setFont(new Font("Stencil",1,50));
		texte.setBorder(BorderFactory.createMatteBorder(0,0,1,0, new Color(180,180,180)));
		this.add (texte, BorderLayout.NORTH);
		this.add(gameV,BorderLayout.CENTER);
		
	}
	

	public static void main ( String [] args){
		JFrame fenetre = new JFrame();		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setMinimumSize(new Dimension(400,300));
		fenetre.setSize(new Dimension(700,500));
		Game g = new Game();
		g.initPlayer();				
		Panel dessin = new Panel(g);
		fenetre.setContentPane(dessin);
		fenetre.pack();
		fenetre.setVisible(true);
		dessin.requestFocus();
	}
}
