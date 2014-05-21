package uk.ac.gcu.battleships.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * It represents a Panel which enable to get all input of the user.
 * @author Yann Prono
 */
public class InputView extends JPanel {
	
	private Instruction instr;
	/** Element for getting input of user. */
	private JTextField input;

	/** Button for validating the input. */
	private JButton enter;
	
	/** String to save content of input. */
	private String content;

	public InputView() {
		this.setLayout(new BorderLayout());
		this.instr = new Instruction();
		this.setMaximumSize(new Dimension(100,50));
		this.input = new JTextField();
		this.input.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			
			}

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					content = ((JTextField)e.getSource()).getText();
					((JTextField)e.getSource()).setText("");
					
			}
			}
			

			public void keyReleased(KeyEvent e) {}
			
		});
		this.input.setFont(new Font("Arial",Font.BOLD,20));
		
		this.enter = new JButton("Enter");
		
		this.enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content = input.getText();
				input.setText("");
			}			
		});
		
		this.add(this.instr,BorderLayout.NORTH);
		this.add(this.input, BorderLayout.CENTER);
		this.add(this.enter, BorderLayout.EAST);
	}

	/**
	 * Getter to get the content of the current input.
	 * @return String.
	 */
	public String getContent() {
		return this.content;
	}
	
	
	public static void main(String[] agrs) {
		JFrame j = new JFrame("rnje");
		j.setContentPane(new InputView());
		j.pack();
		j.requestFocus();
		j.setVisible(true);
	}
}
