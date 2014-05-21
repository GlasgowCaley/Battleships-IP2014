package uk.ac.gcu.battleships.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.ac.gcu.battleships.Game;


public class GameView extends JPanel {
	
	private Game g;
	private PlayerView[] player;
	private InputView input;

	public GameView(Game g) {
		this.g = g;
		this.input = new InputView();
		this.setLayout(new GridLayout(2,1));
		this.player = new PlayerView[2];
		this.player[0] = new PlayerView(g.getPlayer()[0]);
		this.player[1] = new PlayerView(g.getPlayer()[1]);
		
		JPanel main = new JPanel();
		main.add(player[0]);
		this.add(main);
		this.add(this.input);
		
	}
	
	public static void main(String[] args) { 
		Game g = new Game();
		g.initPlayer();
		JFrame f = new JFrame("dfkj");
		GameView gv = new GameView(g);
		
		f.setContentPane(gv);
		gv.requestFocus();
		f.pack();f.setVisible(true);	
	}
}
