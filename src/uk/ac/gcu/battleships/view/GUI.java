package uk.ac.gcu.battleships.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel left_panel = new JPanel();
		frame.getContentPane().add(left_panel, BorderLayout.WEST);
		left_panel.setPreferredSize(new Dimension(500,500));
		
		JPanel right_panel = new JPanel();
		frame.getContentPane().add(right_panel, BorderLayout.EAST);
	}

}
