package uk.ac.gcu.battleships.view;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.Border;

import uk.ac.gcu.battleships.GameConfiguration;
import uk.ac.gcu.battleships.Grid;
import uk.ac.gcu.battleships.Guess;


public class GridView extends JPanel implements Observer {

	private JLabel label[][];
	private Grid g;
	public static Border BLACKLINE = BorderFactory.createLineBorder(new Color(143,144,138),1);
	private boolean action;
	public Icon water = new ImageIcon("images/sea.png");
	public Icon question = new ImageIcon("images/Help-icon.png");
	public Icon hit = new ImageIcon("images/Military-Explosion-icon.png");
	public Icon submarine=new ImageIcon("images/Letter-S-icon.png");
	public Icon destructor=new ImageIcon("images/Letter-D-icon.png");
	public Icon battleship=new ImageIcon("images/Letter-B-icon.png");
	public Icon death=new ImageIcon("images/pirate_flag.png");


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
				char op = array[i][j];
				label[i][j] = new JLabel(Character.toString(array[i][j]));
				/*switch(op){
					case '-':
						label[i][j].setIcon(question);
						break;
					case 'x':
						label[i][j].setIcon(hit);
						break;
					case 'o':
						label[i][j].setIcon(water);
						break;
					case 'D':
						label[i][j].setIcon(destructor);
						break;
					case 'B':
						label[i][j].setIcon(battleship);
						break;
					case 'S':
						label[i][j].setIcon(submarine);
						break;
					case '@':
						label[i][j].setIcon(death);
						break;
					default:
						label[i][j].setIcon(water);
						break;
					
				}*/
				label[i][j].setBorder(GridView.BLACKLINE);
				//this.add(label[i][j]);
				
				label[i][j] = new JLabel(Character.toString(array[i][j]),JLabel.CENTER);
				label[i][j].setFont(new Font("Arial",Font.BOLD,15));
				label[i][j].setForeground(Color.WHITE);
				label[i][j].setBorder(GridView.BLACKLINE);
				this.add(label[i][j]);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}