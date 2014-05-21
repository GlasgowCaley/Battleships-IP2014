package uk.ac.gcu.battleships.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

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
			this.instruction = new JLabel();
			JPanel back = new JPanel (new GridLayout(2,1));
			//JLabel arrow = new JLabel () ;
			//arrow.setIcon(leftArrow);
			//JTextField text = new JTextField ();
			instruction.setText("Do something");
			back.add(instruction);
			//back.add(arrow);
			this.add(back, BorderLayout.CENTER);
			setVisible(true);			
			
		}
		
		
		public void setInstruction(String s)
		{
			this.instruction.setText(s);
		}
	    	
}


