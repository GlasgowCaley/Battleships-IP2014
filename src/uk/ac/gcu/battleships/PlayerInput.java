package uk.ac.gcu.battleships;

import java.util.Scanner;

public class PlayerInput {
	
	public static void inputFleet(Player p){
		String name = "";
		for(int i=2; i<=4; i++){ 
			boolean add = false;
			switch(i) {
			case 2:
				name = "Get ready to place your Destroyer (Size 2)";
				break;
			case 3:
				name = "Get ready to place your Submarine (Size 3)";
				break;
			case 4:
				name = "Get ready to place your Battleship (Size 4)";
				break;
			}

			while(!add) {
				System.out.println(Game.display(p.myGrid.displayOwnGrid()));
				System.out.println(name);				
				add = PlayerInput.inputBoat(p, i);				
			}    		
		}
	}
	public static boolean inputBoat(Player p, int b){
		boolean add = false;
		String msg = "Please enter the couple of coordinate of ship (like 'A1'): "; //+ count
		String msgOrient = "Please enter the orientation of the ship (H or V)";

		int coord [] = PlayerInput.readCoordinate(msg, p.getSize());
		char orient = PlayerInput.readOrientation(msgOrient);

		add  = p.createShip(b,coord[0],coord[1],orient);
		return add;
		
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
			coord = PlayerInput.readCoordinate(msg, p.getSize());
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
	public static int[] readCoordinate(String msg,int m) {
		int[] coord = new int[2];
		Scanner sc = new Scanner(System.in);
		String temp = "";		
		char limit  = (char)(((int)'A')+m-1);
		String regex = "[A-"+ limit +"][1-"+m+"]";
		String regexTwo = "[1-"+m+"][A-"+ limit +"]";
		boolean respect = false;
		char x = 0;
		int y = 0;
		while(!respect) {
			System.out.println(msg);
			temp = sc.next();
			temp = temp.toUpperCase();
			respect = (temp.matches(regex))^(temp.matches(regexTwo));
			if(respect) {
				if(temp.matches(regex)) {
					x = temp.charAt(0);
					y =Character.getNumericValue(temp.charAt(1));	
				}
				else {
					x = temp.charAt(1);
					y =Character.getNumericValue(temp.charAt(0));
				}
				
				
			} 
		}				
		coord[0] = PlayerInput.changeX(x);
		coord[1] = y-1;
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
