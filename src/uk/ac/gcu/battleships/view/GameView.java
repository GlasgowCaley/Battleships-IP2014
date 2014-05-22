package uk.ac.gcu.battleships.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.gcu.battleships.Game;

public class GameView extends JPanel implements Observer {

	public static Game game;
	private JLabel currentPlayer;
	private JPanel main;
	GridView[] grid;

	public static Color BACKGROUND = new Color(245,245,245);  

	public GameView(Game g) {		
		GameView.game = g;
		grid = new GridView[2];
		this.main = new JPanel();	
		this.currentPlayer = new JLabel("Captain "+g.getPlayer()[g.current].name, JLabel.CENTER);		
		this.init();
	}

	private void init() {
		this.main.setBackground(GameView.BACKGROUND);
		this.setLayout(new BorderLayout(2,1));
		this.setPreferredSize(new Dimension(800,300));
		this.setMinimumSize(new Dimension(500,300));
		this.setBackground(GameView.BACKGROUND);
		this.currentPlayer.setFont(new Font("Stencil",Font.BOLD,15));
		this.currentPlayer.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));		
		this.setBackground(GameView.BACKGROUND);
		this.add(this.currentPlayer, BorderLayout.NORTH);
		this.displayGrid();
	}

	public void displayGrid() {
		main.removeAll();
		grid[0] = new GridView(game.getPlayer()[game.current].opponentGrid.displayEnnemyGrid(),true);			
		grid[1] = new GridView(game.getPlayer()[game.current].myGrid.displayOwnGrid(),false);
		main.add(grid[0]);
		main.add(grid[1]);
		this.add(main, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	public void update(Observable o, Object arg) {		
		this.transition();
	}

	public void transition() {
		this.currentPlayer.setText("Captain "+game.getPlayer()[game.current].name);
		this.main.removeAll();
		JButton next = new JButton("Continue");		
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayGrid();			
			}

		});
		this.main.add(next);
		this.add(main);
		this.repaint();		
	}

}

