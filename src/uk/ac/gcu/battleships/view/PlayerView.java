package uk.ac.gcu.battleships.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.gcu.battleships.Grid;
import uk.ac.gcu.battleships.Player;
import uk.ac.gcu.battleships.Submarine;

/**
 * This class represents the view of a Player in-game.
 * @author Yann Prono
 */
public class PlayerView extends JPanel {
	
	private GridView own;
	private GridView ennemy;
	Player p;
	
	public PlayerView(Player p) {
		super();
		this.p = p;
		this.own = new GridView(p.myGrid,p.myGrid.displayOwnGrid(),false);
		this.ennemy = new GridView(p.myGrid,p.opponentGrid.displayEnnemyGrid(),true);		
		this.add(ennemy);
		this.add(own);
		//this.add(new JLabel("Ennemy Grid",JLabel.LEFT));
		//this.add(new JLabel("State of you fleet",JLabel.RIGHT));
	}
	
	public GridView getOwnGrid(){
		return this.own;
	}
	

}

