package uk.ac.gcu.battleships;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the game board.
 * A Grid will contain information about the state of the game,
 * including position of ships, hits and misses.
 * There is one Grid for one player.
 * @author Yann Prono
 * @author Anthony Cerf
 * @version 1.0
 */
public class Grid {
	
	/** Two-dimensional array which represents the grid. */
	private char[][] board;

	/** Default character used in the Grid. */
	public static final char DEFAULT_CHAR = '-';
	
	/** Character used for a Ship which is hit. */
	public static final char HIT = 'x';
	
	/** Character used for a hit missed. */
	public static final char HIT_MISSED = 'o';

	/** List of all Ships in the Grid. */
	private List <Ship> ships;


	/**
	 * Constructor which creates the square array which represents the game board.
	 * @param size Array's size.
	 */
	public Grid(int size) {
		this.board = new char[size][size];

		/*for(int i = 0; i<size;i++){
			for(int j = 0;j<size;j++) {
				this.board[i][j] = Grid.DEFAULT_CHAR;
			}
		}*/
		Grid.FillArray(this.board);

		this.ships = new ArrayList <Ship>();
	}
	
	/**
	 * Method returning the character at a specified position.
	 * @param line Integer corresponding at the number of the line.
	 * @param row Integer corresponding at the number of the row.
	 * @return the character at the specified position.
	 */
	public char returnCharacter(int line, int row) {
		char res = 0;
		try {
			res = this.board[line][row];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			res = 0;
		}
		return res;
	}
	
	/**
	 * Method filling the two dimensional array with dash.
	 */
	private static void initArray(char[][] array) {
		for(int i = 0; i<array.length;i++){
			for(int j = 0;j<array[i].length;j++) {
				array[i][j] = Grid.DEFAULT_CHAR;
			}
		}
	}

	/**
	 * Method displaying the grid into the console.
	 */
	public String toString(){
		// Format
		String format = "%-4s";

		String res = new String(String.format(format,""));;
		char row = 'A';
		int line = 1;

		//loop to display row letter
		for(int i = 0; i<this.board.length;i++){
			res += String.format(format,row);
			row++;
		}

		//loop to display line number and each character
		for(int i = 0; i<this.board.length;i++){
			res+= System.getProperty("line.separator");
			res += String.format(format,line);

			for(int j = 0; j<this.board[i].length;j++){
				res += String.format(format,this.board[i][j]);
			}

			line++;
		}

		// Word wrap
		res+= System.getProperty("line.separator");

		return res;
	}

	/**
	 * Method adding a Ship in this Grid and in the list. 
	 * @param s Ship to add.
	 * @return {@code true} only if the Ship has been added in the Grid.
	 */
	public boolean addShip (Ship s) {
		boolean add = this.checkPosition(s);
		// If the Ship can be added ==> add in the List and in the Grid.
		if(add) {
			// add to the list
			this.ships.add(s);
			//add  in the board
			this.addShipIntoGrid(s);
		}
		else {
			System.out.println("***** YOU CAN'T ADD THIS SHIP AT THE DEFINED POSITION *****");
		}
		return add ;
	}

	/**
	 * Method which changes chars in the Grid with the position's Ship.
	 * This method is called only if the Ship can be added in the Grid. 
	 * @param s Ship to add.
	 */
	private void addShipIntoGrid(Ship s) {
		boolean horizon = s.shipOrientation == 'h';

		if(horizon) {
			for(int i =s.shipPosition_x; i <s.shipPosition_x+s.shipSize;i++) {
				this.board[s.shipPosition_y][i] = 's';	
			}
		}
		else {
			for(int i =s.shipPosition_y; i <s.shipPosition_y+s.shipSize;i++) {
				this.board[i][s.shipPosition_x] = 's';
			}
		}
	}
	
	/**
	 * Method enables to check if the Ship can be added in the Grid
	 * @param s Ship to add
	 * @return {@code true} only if the Ship can be added.
	 */
	private boolean checkPosition(Ship s) {
		boolean add = true;
		boolean horizon = s.shipOrientation == 'h';
		// Horizontal ship
		if(horizon) {
			int i = s.shipPosition_x;
			while(i <s.shipPosition_x+s.shipSize && add) {
				char res = this.returnCharacter(i, s.shipPosition_y);
				if(res != Grid.DEFAULT_CHAR || res == 0) {
					add = false;	
				}
				i++;
			}
		}
		// Vertical ship
		else {
			int i = s.shipPosition_y;
			while(i <s.shipPosition_y+s.shipSize && add) {
				char res = this.returnCharacter(s.shipPosition_x, i);
				if(res != Grid.DEFAULT_CHAR || res == 0) {
					add = false; 
				}
				i++;
			}	
		}
		return add;
	}
	
	/**
	 * Method enables to know if the player's guess
	 * hit a Ship in this Grid.   
	 * @param g Guess of the ennemy player.
	 * @return {@ode true} if all ships have been sunk.
	 */
	public boolean checkGuess(Guess g) {
		int x = g.get_X();
		int y = g.get_Y();
		int l = this.ships.size();
		boolean hit = false;
		boolean sunk = true;
		int i = 0;
		
		while(!hit && i<l){
			hit = this.ships.get(i).testHit(x, y);
			if(hit) 
				this.board[y][x] = Grid.HIT;
			else
				this.board[y][x] = Grid.HIT_MISSED;
			i++;
		}
		
		for(int j = 0;j<l;j++) {
			if(this.ships.get(j).isSunk())
				sunk = false;
		}
		
		return sunk;		
	}
	
	/**
	 * Method displaying the Grid player into the console.
	 */
	public void displayOwnGrid(){
		// Format
		String format = "%-4s";

		String res = new String(String.format(format,""));;
		char row = 'A';
		int line = 1;

		//loop to display row letter
		for(int i = 0; i<this.board.length;i++){
			res += String.format(format,row);
			row++;
		}

		//loop to display line number and each character
		for(int i = 0; i<this.board.length;i++){
			res+= System.getProperty("line.separator");
			res += String.format(format,line);

			for(int j = 0; j<this.board[i].length;j++){
				if(this.returnCharacter(i, j) != Grid.HIT_MISSED)
					res += String.format(format,this.returnCharacter(i, j));
				else 
					res += String.format(format,Grid.DEFAULT_CHAR);
					
			}

			line++;
		}

		// Word wrap
		res+= System.getProperty("line.separator");

		System.out.println(res);
	}
	
	/**
	 * Method displaying the ennemy grid into the console.
	 */
	public void displayEnnemyGrid(){
		// Format
		String format = "%-4s";

		String res = new String(String.format(format,""));;
		char row = 'A';
		int line = 1;

		//loop to display row letter
		for(int i = 0; i<this.board.length;i++){
			res += String.format(format,row);
			row++;
		}

		//loop to display line number and each character
		for(int i = 0; i<this.board.length;i++){
			res+= System.getProperty("line.separator");
			res += String.format(format,line);

			for(int j = 0; j<this.board[i].length;j++){
				char c = this.returnCharacter(i, j);
				if(c == Grid.HIT || c== Grid.HIT_MISSED)
					res += String.format(format,this.returnCharacter(i, j));
				else 
					res += String.format(format,Grid.DEFAULT_CHAR);	
			}
			line++;
		}

		// Word wrap
		res+= System.getProperty("line.separator");

		System.out.println(res);
	}
	
	/**
	 * Getter for the Two-dimensional array
	 * @return The array which represents the Grid. 
	 */
	public char[][] getBoard() {
		return this.board;
	}
	
	/**
	 * Getter for the List of Ships which are on the Grid.
	 * @return The list of Ships. 
	 */
	public List<Ship> getShips() {
		return this.ships;
	}
	
}
