package uk.ac.gcu.battleships;

import java.util.Scanner;

public class PlayerInput {

	public static void inputFleet(Player p){
		String nameBoat = "";
		for(int i=0; i<GameConfiguration.gameConfiguration_SIZE; i++){ 
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
			}    		
		}
	}


	public static void inputGuess(Guess g, Player p) {
		Scanner sc = new Scanner(System.in);
		String dec = "      ";
		Game.clearConsole(); // Empty the screen		
		System.out.println(dec+"-- Captain " +p.name+" --"); // Display name of player whose turn it is
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
	 * Method which enables to read the X axis.
	 * @return the integer for the X axis.
	 */	
	public static int[] readCoordinate(String msg) {
		Scanner sc = new Scanner(System.in);
		String temp = "";		
		boolean respect = false;
		char x = 0;
		int y = 0;
		while(!respect) {
			System.out.println(msg);
			temp = sc.next();
			temp = temp.toUpperCase();
			respect = PlayerInput.checkRegex(temp);
 
		}
		
		int[] coord = PlayerInput.separateCoordinate(temp);
		for(int i =0;i<coord.length;i++) {
			System.out.println(coord[i]);
		}
		return coord;
	}

	public static boolean checkRegex(String s) {
		int size = GameConfiguration.gameConfiguration_SIZE;
		int max = String.valueOf(size).length();
		char limit  = (char)(((int)'A')+size-1);
		String regex = "[A-"+ limit +"][1-"+size+"]";
		String regexTwo = "[1-"+size+"][A-"+ limit +"]";
		boolean match = false;
		if(s.matches(regex)) {
			match = true;
		}	
		if(s.matches(regexTwo)) {
			match = true;
			s = Character.toString(s.charAt(1))+Character.toString(s.charAt(0));
		}	
		return match;		
	}
	
	public static int[] separateCoordinate(String s) {
		//int size = GameConfiguration.gameConfiguration_SIZE;
		int coord[] = new int[2];
		coord[0] = PlayerInput.changeX(s.charAt(0));
		coord[1] = Character.getNumericValue(s.charAt(1))-1;
		return coord;	
	}

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
