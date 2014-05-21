package uk.ac.gcu.battleships.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import uk.ac.gcu.battleships.GameConfiguration;
import uk.ac.gcu.battleships.Grid;
import uk.ac.gcu.battleships.Guess;
import uk.ac.gcu.battleships.Submarine;

public class GridView extends JPanel implements Observer {

	private JLabel label[][];
	private Grid g;
	public static Border BLACKLINE = BorderFactory.createLineBorder(new Color(143,144,138),1);
	private boolean action;

	/**
	 * Constructor 
	 * @param p Current player.
	 */
	public GridView(Grid g,char[][] array,boolean action) {
		super();
		this.setPreferredSize(new Dimension(500,300));
		this.g = g;
		this.setBackground(new Color(39,40,34));
		this.init(array);
		this.action = action;
		if(action) {
			this.initActionListener();
		}
	}


	private void initActionListener() {
		int size = GameConfiguration.gameConfiguration_SIZE;
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				label[i][j].addMouseListener ( new MouseAdapter() {

					public void mousePressed ( MouseEvent e )
					{	
						System.out.println("ok");
						JLabel l = (JLabel)e.getSource();
						int[] coord = search(l);
						System.out.println(coord[0]);
						System.out.println(coord[1]);
						System.out.println(g.checkGuess(new Guess(coord[0],coord[1])));
					}


				});
			}
		}

	}

	public int[] search(JLabel l) {
		int x = 0;
		int y = 0;
		int size = GameConfiguration.gameConfiguration_SIZE;
		boolean found = false;
		int i = 0,j = 0;
		while(!found && i<size) {
			j = 0;
			while(!found && j<size) {
				if(this.label[i][j].equals(l)) 
					found = true;				
				j++;
			}			
			i++;
		}

		if(found) {
			x = j-1;
			y = i-1;
		}
		return new  int[]{x,y};

	}


	private void init(char[][] array) {
		int size = GameConfiguration.gameConfiguration_SIZE;
		this.label = new JLabel[size][size];
		GridLayout layout = new GridLayout(size,size);
		this.setLayout(layout);
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				label[i][j] = new JLabel(Character.toString(array[i][j]),JLabel.CENTER);
				label[i][j].setFont(new Font("Arial",Font.BOLD,15));
				label[i][j].setForeground(Color.WHITE);
				label[i][j].setBorder(GridView.BLACKLINE);
				this.add(label[i][j]);
			}
		}
	}


	public static void main(String[] args) {
		JFrame f = new JFrame("dfkj");
		Grid g = new Grid();
		
		JPanel j = new JPanel();
		j.setBackground(new Color(39,40,34));
		j.setLayout(new BorderLayout());
		g.addShip(new Submarine(0,0,'H'));
		GridView v = new GridView(g,g.displayOwnGrid(),false);
		GridView v1 = new GridView(g,g.displayEnnemyGrid(),true);

		j.add(v,BorderLayout.EAST);
		j.add(v1,BorderLayout.WEST);
		f.setContentPane(j);
		j.requestFocus();
		f.pack();f.setVisible(true);
	}


	@Override
	public void update(Observable o, Object arg) {
		if(this.action) {
			this.g.
		}
		
	}
}