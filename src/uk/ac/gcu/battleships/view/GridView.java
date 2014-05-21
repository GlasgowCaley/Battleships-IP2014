package uk.ac.gcu.battleships.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import uk.ac.gcu.battleships.GameConfiguration;
import uk.ac.gcu.battleships.Grid;
import uk.ac.gcu.battleships.*;

public class GridView extends JPanel {
	
	private JLabel label[][];

	public GridView(char array[][]) {
		
		int size = GameConfiguration.gameConfiguration_SIZE;
		this.label = new JLabel[size][size];
		GridLayout layout = new GridLayout(size,size);
		this.setLayout(layout);
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				label[i][j] = new JLabel(Character.toString(array[i][j]));
				label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				this.add(label[i][j]);
			}
		}
	}
	
	public static void main (String[] args) {
		Grid g = new Grid();
		GridView gv = new GridView(g.displayOwnGrid());
		JFrame frame = new JFrame()	;

		frame.setContentPane(gv);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
}
