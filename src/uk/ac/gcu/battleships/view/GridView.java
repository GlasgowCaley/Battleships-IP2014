package uk.ac.gcu.battleships.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import uk.ac.gcu.battleships.GameConfiguration;

public class GridView extends JPanel {

	private JLabel label[][];
	private JPanel grid;
	private JPanel infos;
	private boolean action;
	
	public static Border BLACKLINE = BorderFactory.createLineBorder(new Color(143,144,138),1);
	

	/**
	 * Constructor *
	 * @param p Current player.
	 */
	public GridView(char[][] array,boolean action) {
		super();
		this.grid = new JPanel();
		this.infos = new JPanel();
		this.setPreferredSize(new Dimension(500,500));
		this.setBackground(new Color(39,40,34));
		this.grid.setBackground(new Color(39,40,34));
		this.action = action;
		this.init(array);		
		if(this.action) {
			this.initActionListener();
		}
	}
	


	private void initActionListener() {
		int size = GameConfiguration.gameConfiguration_SIZE;
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				label[i][j].addMouseListener ( new GameController());
				
			}
		}

	}

	private void init(char[][] array) {
		int size = GameConfiguration.gameConfiguration_SIZE;
		this.label = new JLabel[size][size];
		this.grid.setLayout(new GridLayout(size,size));
		this.infos.setLayout(new GridLayout(1,1));
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				label[i][j] = new JLabel(Character.toString(array[i][j]),JLabel.CENTER);
				label[i][j].setBorder(GridView.BLACKLINE);
				label[i][j].setFont(new Font("Arial",Font.BOLD,15));
				label[i][j].setForeground(Color.WHITE);					
				label[i][j].setOpaque(false);
				label[i][j].setName(String.valueOf(j)+String.valueOf(i));
				label[i][j].setBorder(GridView.BLACKLINE);
				this.grid.add((label[i][j]));
			}
		}
		this.add(this.grid,BorderLayout.CENTER);
		System.out.println("method");
		System.out.println(this.action);
		if(this.action)
			
			this.infos.add(new JLabel("Click on the left grid to make a guess",JLabel.CENTER));
		else 
			this.infos.add(new JLabel("State of your fleet",JLabel.CENTER));
		this.add(this.infos,BorderLayout.SOUTH);
	}
	
	public JLabel[][] getGrid() {
		return this.label;
	}

}