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
	 * Constructor which creates the square array which represents the game board
	 * By default, the size of the grid is 5.
	 */
	public Grid() {
		this.board = new char[5][5];
		Grid.initArray(this.board);
		this.ships = new ArrayList <Ship>();
	}

	/**
	 * Constructor which creates the square array which represents the game board.
	 * @param size Array's size.
	 */
	public Grid(int size) {
		this.board = new char[size][size];
		Grid.initArray(this.board);
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
	 * Method filling the two dimensional array with the default character.
	 * @param array Array to fill.
	 */
	private static void initArray(char[][] array) {
		for(int i = 0; i<array.length;i++){
			for(int j = 0;j<array[i].length;j++) {
				array[i][j] = Grid.DEFAULT_CHAR;
			}
		}
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
			System.out.println(" /!\\You can't add this ship at the defined position.\n");
		}
		return add ;
	}

	/**
	 * Method which changes characters in the Grid with the position's Ship.
	 * This method is called only if the Ship can be added in the Grid. 
	 * @param s Ship to add.
	 */
	private void addShipIntoGrid(Ship s) {
		boolean horizon = Character.toUpperCase(s.shipOrientation) == 'H';

		if(horizon) {
			for(int i =s.shipPosition_x; i <s.shipPosition_x+s.shipSize;i++) {
				this.board[s.shipPosition_y][i] = s.character;	
			}
		}
		else {
			for(int i =s.shipPosition_y; i <s.shipPosition_y+s.shipSize;i++) {
				this.board[i][s.shipPosition_x] = s.character;
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
		boolean horizon = Character.toUpperCase(s.shipOrientation) == 'H';
		// Horizontal ship
		if(horizon) {
			int i = s.shipPosition_x;
			while(i <s.shipPosition_x + s.shipSize && add) {

				char res = this.returnCharacter(s.shipPosition_y,i);
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
				char res = this.returnCharacter(i,s.shipPosition_x);
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
			if(hit && (this.returnCharacter(y, x) != Grid.HIT)) { 
				this.board[y][x] = Grid.HIT;
				if(this.ships.get(i).isSunk()) 
					System.out.println("A Ship has been sunk!");			
			}
			else {
				this.board[y][x] = Grid.HIT_MISSED;
			}

			i++;
		}
		if(!hit) 
			System.out.println("Miss!");

		for(int j = 0;j<l;j++) {
			if(!this.ships.get(j).isSunk())
				sunk = false;
		}

		return sunk;		
	}

	/**
	 * Method displaying the Grid player into the console.	
	 * @return The array for the own player.
	 */
	public char[][] displayOwnGrid(){
		int length = this.board.length;
		char[][] array = new char[length][length];		
		Grid.initArray(array);

		for(int i = 0; i<this.board.length;i++){
			for(int j = 0; j<this.board[i].length;j++){
				char tmp = this.returnCharacter(i, j);			
				if(tmp == Grid.HIT || tmp == 'S' || tmp == 'D' || tmp == 'B')
					array[i][j] = tmp;
			}
		}
		return array;
	}

	/**
	 * Method displaying the ennemy grid into the console.
	 * @return : 2D Array which represents the Opponent's grid, with hit and misses
	 */
	public char[][] displayEnnemyGrid(){
		// Format

		char[][] array = new char[this.board.length][this.board.length];
		Grid.initArray(array);


		//loop to display line number and each character
		for(int i = 0; i<this.board.length;i++){
			for(int j = 0; j<this.board[i].length;j++){
				char c = this.returnCharacter(i, j);
				if(c == Grid.HIT || c== Grid.HIT_MISSED){
					array[i][j] = c;
				}
			}
		}
		return array ;		
	}

	public char checkGuessAI(Guess g) {
		int x = g.get_X();
		int y = g.get_Y();
		int l = this.ships.size();
		boolean hit = false;
		boolean miss = false;
		boolean sunk = true;
		char charac = 'm';
		int i = 0;

		while(!hit && i<l){
			hit = this.ships.get(i).testHit(x, y);
			if(hit && (this.returnCharacter(y, x) != Grid.HIT)) { 
				this.board[y][x] = Grid.HIT;
				System.out.println("You have hit a Ship!");
				charac = 'h';
				if(this.ships.get(i).isSunk()) {
					System.out.println("Sunk!");
					charac = 's';
				}
			}
			else {
				this.board[y][x] = Grid.HIT_MISSED;
			}

			i++;
		}
		if(this.returnCharacter(y, x) == Grid.HIT_MISSED){ 
			System.out.println("Miss!");
			charac = 'm';
		}

		for(int j = 0;j<l;j++) {
			if(!this.ships.get(j).isSunk())
				sunk = false;
		}
		if (sunk == true)
		{
			charac = 'w';
		}
		return charac;		
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
