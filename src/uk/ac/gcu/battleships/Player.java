package uk.ac.gcu.battleships;

import java.util.Scanner;

public class Player  {
	public String name;
	public Grid myGrid;
	public Grid opponentGrid;
	private int size;

	public Player(){
		this.inputName();
		myGrid = new Grid();
		this.size = this.myGrid.getBoard().length;
	}

	public void inputName() {
		Scanner sc = new Scanner(System.in);
		name = sc.next(); // Save name of the player
		char f = name.charAt(0);
		name = Character.toUpperCase(f) + name.substring(1,name.length());
	}

	public boolean createShip(int size, int x, int y, char orientation){
		Ship S = new Ship(size, x, y, orientation); // Create new Ship S
		return myGrid.addShip(S);
	}

	public boolean makeGuess(Guess G){
		this.enterGuess(G);
		return opponentGrid.checkGuess(G);
	}

	public void addFleet(){ // Creates a fleet of boats

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
				System.out.println(this.myGrid);
				System.out.println(name);				
				add = this.addBoat(i);				
			}    		
		}
	}

	public boolean addBoat(int b){
		boolean add = false;
		String msg = "Please enter the couple of coordinate of ship ('A1'): "; //+ count
		String msgOrient = "Please enter the orientation of the ship (H or V)";

		int coord [] = Game.readCoordinate(msg, this.size);
		char orient = Game.readOrientation(msgOrient);

		add  = this.createShip(b,coord[0],coord[1],orient);
		return add;
	}

	public void enterGuess(Guess g) {
		Scanner sc = new Scanner(System.in);
		String dec = "      ";
		Game.clearConsole(); // Empty the screen		
		System.out.println(dec+"-- Captain " +this.name+" --"); // Display name of player whose turn it is
		sc.nextLine();
		System.out.println(dec+"State of your fleet"); // Display name of player whose turn it is
		System.out.print(Game.display(this.myGrid.displayOwnGrid())); // Show own grid
		System.out.println("\n"+dec+"State of the fight"); // Display name of player whose turn it is
		System.out.println(Game.display(this.opponentGrid.displayEnnemyGrid())); // Show enemy grid
		String msg = "Enter coordinates of you guess (like 'A1'): ";
		int[] coord = Game.readCoordinate(msg, this.size);
		g.set_X(coord[0]);
		g.set_Y(coord[1]);
	}
}
