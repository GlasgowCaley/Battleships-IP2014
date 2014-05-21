package uk.ac.gcu.battleships.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import uk.ac.gcu.battleships.*;

import java.awt.BorderLayout;

/**
 * For GUI
 * @author Robert Morrison
 * @author Tuomas Kinnunen
 * 
 */
public class Battleships {
	public Battleships() {				
		Grid g = new Grid();
		GridView og = new GridView(g.displayOwnGrid());
		GridView eg = new GridView(g.displayEnnemyGrid());
		JFrame frame = new JFrame()	;
		JPanel back = new JPanel(new GridLayout(1,3));
		MainPanel panel = new MainPanel();
		back.add(og);
		back.add(panel);

		back.add(eg);
		frame.setContentPane(back);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/** Main method */
	public static void main (String[] args) {
		new Battleships();
		
	}
}
