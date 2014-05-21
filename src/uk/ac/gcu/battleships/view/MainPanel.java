package uk.ac.gcu.battleships.view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Observer {
	
	private String message;
	
	private Instruction instruction;
	
	private InputView input;
	

	public MainPanel() {
		this.message = "";
		this.instruction = new Instruction();		
		this.input = new InputView();
		
		this.setLayout(new GridLayout(2,1));
		this.add(this.instruction);
		this.add(this.input);
	}
	

	public static void main(String[] agrs) {		
		GameController g = new GameController();		
		MainPanel  p = new MainPanel();
		g.addObserver(p);
		g.setMessage("Hola");
		JFrame j = new JFrame("rnje");
		j.setContentPane(p);
		j.pack();
		j.requestFocus();
		j.setVisible(true);						
	}

	public void update(Observable o, Object arg) {
		String s = (String)arg;
		this.instruction.setInstruction(s);
	}

}
