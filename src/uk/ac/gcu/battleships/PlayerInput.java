package uk.ac.gcu.battleships;

import java.util.Scanner;

/**
 * This class contains all methods
 * to manage all inputs of a Player.
 * @author Yann Prono
 */
public class PlayerInput {
	
	/** String for keeping the same content through some methods. */	 
	private static String input;

	/**
	 * Method which add the Fleet into the Grid.
	 * This method manages all input for coordinates
	 * orientation, et displays message if the Ship can't be added.
	 * @param p The current Player.
	 */
	public static void inputFleet(Player p){
		String nameBoat = "";
		for(int i=0; i<GameConfiguration.gameConfiguration_NBSHIP; i++){ 
			boolean add = false;
			while(!add) {
				Ship s = null;
				System.out.println(Game.display(p.myGrid.displayOwnGrid()));							

				switch(i) {
				case 0:
					nameBoat = "Get ready to place your Destroyer (Size 2)";
					break;
				case 1:
					nameBoat = "Get ready to place your Submarine (Size 3)";
					break;
				case 2:
					nameBoat = "Get ready to place your Battleship (Size 4)";
					break;
				}

				System.out.println(nameBoat);
				String msg = "Please enter the couple of coordinate of ship (like 'A1'): "; //+ count
				String msgOrient = "Please enter the orientation of the ship (H or V)";
				int coord [] = PlayerInput.readCoordinate(msg);
				char orient = PlayerInput.readOrientation(msgOrient);
				switch(i) {
				case 0:
					s = new Destroyer(coord[0],coord[1],orient);
					break;
				case 1:
					s = new Submarine(coord[0],coord[1],orient);					
					break;
				case 2:
					s = new Battleship(coord[0],coord[1],orient);					
					break;
				}

				add = p.addShip(s);
				if(!add) {
					System.out.println("/!\\You can't add this ship at the defined position.\n");
					Scanner sc = new Scanner(System.in);
					sc.nextLine();
				}
			}    		
		}
	}

	/**
	 * Method which enable for a Player to input a guess.
	 * @param g Current Guess.
	 * @param p The current Player.
	 */
	public static void inputGuess(Guess g, Player p) {
		Scanner sc = new Scanner(System.in);
		String dec = "      ";
		Game.clearConsole(); // Empty the screen		
		System.out.println(dec+"-- Captain " +p.name+", press enter to proceed --"); // Display name of player whose turn it is
		sc.nextLine();
		System.out.println(dec+"State of your fleet"); // Display name of player whose turn it is
		System.out.print(Game.display(p.myGrid.displayOwnGrid())); // Show own grid
		System.out.println("\n"+dec+"State of the fight"); // Display name of player whose turn it is
		System.out.println(Game.display(p.opponentGrid.displayEnnemyGrid())); // Show enemy grid
		boolean same = false;
		String msg = "Enter coordinates of you guess (like 'A1'): ";
		int[] coord = new int[2];
		while (!same) {
			coord = PlayerInput.readCoordinate(msg);
			char c = p.opponentGrid.returnCharacter(coord[1], coord[0]);
			if(c == Grid.HIT_MISSED || c == Grid.HIT) {
				same = false;
				System.out.println("You have already hit this position, Enter an other position.");
			}
			else  {
				same = true;

			}
		}
		g.set_X(coord[0]);
		g.set_Y(coord[1]);
	}

	/**
	 * Method which enables to read couple of coordinates (X and Y).
	 * @return the array which contains the X axis (index 0) and Y axis (index 1).
	 */	
	public static int[] readCoordinate(String msg) {
		Scanner sc = new Scanner(System.in);
		input = "";		
		boolean respect = false;
		while(!respect) {
			System.out.println(msg);
			input = sc.next();
			input = input.toUpperCase();
			respect = PlayerInput.checkRegex();
		}
		int[] coord = PlayerInput.separateCoordinate();
		return coord;
	}

	/**
	 * Method which checks if the couple of coordinates
	 * respect all conventions.
	 * @return {@code true} only if the content respect the 
	 * regular expression.
	 */
	public static boolean checkRegex() {
		int size = GameConfiguration.gameConfiguration_SIZE;
		char limit  = (char)(((int)'A')+size-1);
		String regex = "[A-"+ limit +"][1-"+size+"]";
		String regexTwo = "[1-"+size+"][A-"+ limit +"]";
		boolean match = false;
		if(input.matches(regex)) {
			match = true;
		}	
		if(input.matches(regexTwo)) {
			match = true;
			String temp = Character.toString(input.charAt(1))+Character.toString(input.charAt(0));
			input = temp;			
		}	
		return match;		
	}
	
	/**
	 * Method separating the X and Y axis into an array.
	 * @return the array corresponding at coordinates.
	 */
	public static int[] separateCoordinate() {
		//int size = GameConfiguration.gameConfiguration_SIZE;
		int coord[] = new int[2];
		coord[0] = PlayerInput.changeX(input.charAt(0));
		coord[1] = Character.getNumericValue(input.charAt(1))-1;
		
		return coord;	
	}

	/**
	 * Method reading the wanted orientation for a Ship.
	 * @param msg Message to display before input.
	 * @return the Character of the input.
	 */
	public static char readOrientation(String msg) {
		Scanner sc = new Scanner(System.in);
		String temp = "";
		char c = 0;
		boolean ok = false;
		while (!ok){			
			System.out.println(msg);
			temp = sc.next();
			c = temp.toUpperCase().charAt(0);
			ok = (c == 'V' || c == 'H');			
		}		
		return c;		
	}

	/** Changes any letter into a number */
	public static int changeX(char c){ 
		int i=0;
		boolean enc=false;
		while(i<=27 && !enc){
			if(c-'a'==i||c-'A'==i) 
				enc=true;
			else
				i++;
		}
		return i;
	}
}
