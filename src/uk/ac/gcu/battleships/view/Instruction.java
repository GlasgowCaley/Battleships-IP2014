package uk.ac.gcu.battleships.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

import com.sun.glass.events.*;
import com.sun.javafx.applet.Splash;

import java.awt.*;
import java.awt.event.WindowAdapter;

import com.sun.glass.events.WindowEvent;

public class Instruction extends JPanel {

	//Insanciar objetos del juego
		private JLabel instruction;
		public Icon water = new ImageIcon("images/sea.png");
		public Icon help = new ImageIcon("images/Help-icon.png");
		
		public Icon leftArrow = new ImageIcon("images/LeftArrow.png");
		public Icon rigthArrow = new ImageIcon("images/LeftArrow.png");

		
		public Instruction(){
			super();
			JLabel instruction = new JLabel();
			JPanel back = new JPanel (new GridLayout(2,1));
			JLabel arrow = new JLabel () ;
			arrow.setIcon(leftArrow);
			//JTextField text = new JTextField ();
			instruction.setText("Do something");
			back.add(instruction);
			back.add(arrow);
			this.add(back, BorderLayout.CENTER);
			setVisible(true);			
			
		}
		public void setInstruction(String s)
		{
			this.instruction.setText(s);
		}
	    static JFrame frame = new JFrame("Juego");
		public static void main(String[] args) {
		
			// TODO Auto-generated method stub
			Instruction mm = new Instruction();
			 frame.setUndecorated(true); // Remove title bar
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		        frame.addWindowListener(new WindowAdapter() {
		            public void windowClosing(WindowEvent windowEvent) {
		                System.exit(0);
		            }
		        });
		        frame.setResizable(false);
		        frame.getContentPane().add(mm);
		        frame.pack();
		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
		    }
		

}


