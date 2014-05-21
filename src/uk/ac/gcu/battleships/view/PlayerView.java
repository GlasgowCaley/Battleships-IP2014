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
		this.setLayout(new BorderLayout());
		JPanel main = new JPanel();		
		this.own = new GridView(p.myGrid,p.myGrid.displayOwnGrid(),false);
		this.ennemy = new GridView(p.myGrid,p.opponentGrid.displayEnnemyGrid(),true);
		main.add(ennemy);
		main.add(own);
		/*this.add(ennemy);
		this.add(own);*/
		this.add(main,BorderLayout.CENTER);
		JPanel infos = new JPanel();
		infos.setLayout(new GridLayout(1,1));
		
		infos.add(new JLabel("Ennemy fleet",JLabel.CENTER));
		infos.add(new JLabel("State of your fleet",JLabel.CENTER));
		this.add(infos,BorderLayout.SOUTH);
		//this.add(new JLabel("Ennemy Grid",JLabel.LEFT));
		//this.add(new JLabel("State of you fleet",JLabel.RIGHT));
	}
	
	public GridView getOwnGrid(){
		return this.own;
	}
	

}

