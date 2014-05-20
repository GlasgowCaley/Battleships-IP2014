package uk.ac.gcu.battleships;

import java.util.Scanner;

public class PlayerInput {
	public void inputFleet(Player p){
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
				add = this.inputBoat(p, i);				
			}    		
		}
	}
	public boolean inputBoat(Player p, int b){
		boolean add = false;
		String msg = "Please enter the couple of coordinate of ship (like 'A1'): "; //+ count
		String msgOrient = "Please enter the orientation of the ship (H or V)";

		int coord [] = Game.readCoordinate(msg, p.getSize());
		char orient = Game.readOrientation(msgOrient);

		add  = p.createShip(b,coord[0],coord[1],orient);
		return add;
		
	}
	
	public void inputGuess(Guess g, Player p) {
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
			coord = Game.readCoordinate(msg, p.getSize());
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
}
