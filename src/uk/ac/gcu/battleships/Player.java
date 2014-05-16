package uk.ac.gcu.battleships;

import java.util.Scanner;
import java.io.*;

public class Player  {
	public String name;
	public Grid myGrid;
	public Grid opponentGrid;

	public Player(){
		this.inputName();
		myGrid = new Grid();
	}

	public void inputName() {
		Scanner sc = new Scanner(System.in);
		name = sc.next(); // Save name of the player
		while(name.length() ==0) {
			name = sc.next(); // Save name of the player			
		}
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
		Scanner input = new Scanner(System.in);
		String temp;
		boolean add = false;

		// Get starting position of ship
		char c = 0 ;		
		String letter = "Please enter the horizontal (A to E) coordinate of ship"; //+ count
		String number = "Please enter the vertical (1 to 5) coordinate of ship";
		
		int x = Game.readXAxis(letter); // Get X Axis value, save to x
		int y = Game.readYAxis(number); // Get Y Axis value, save to y

		// Get orientation of ship - Horizontal or Vertical
		System.out.println("Please enter the orientation of the ship (H or V)" ); //+ count
		input = new Scanner(System.in);
		temp = input.next(); // 
		char orient = temp.toUpperCase().charAt(0);

		//Not proud of this while loop... Fix into test methods!

		while((orient != 'H' && orient != 'V') && (temp.length() != 1)) {
			System.out.println("That is not a valid orientation ");
			System.out.println("Please enter the orientation of the ship (H or V)" ); //+ count
			temp = input.next();
			orient = temp.toUpperCase().charAt(0); // change lower case v/h to upper case V/H
		}

		//call player.createShip method
		add  = this.createShip(b,x,y,orient);
		return add;
	}


	public void enterGuess(Guess g) {
		String dec = "      ";
		Scanner sc = new Scanner(System.in);
		Game.clearConsole(); // Empty the screen		
		System.out.println(dec+"-- Captain " +this.name+" --"); // Display name of player whose turn it is
		sc.nextLine();
		System.out.println(dec+"State of your fleet"); // Display name of player whose turn it is
		System.out.print(Game.display(this.myGrid.displayOwnGrid())); // Show own grid
		System.out.println("\n"+dec+"State of the fight"); // Display name of player whose turn it is
		System.out.println(Game.display(this.opponentGrid.displayEnnemyGrid())); // Show enemy grid
		System.out.println("\tYour guess\n"); // Ask for guess
		boolean same = false;

		while(!same) {
			// 	Read the guess
			String letter = "Enter the letter coordinate : ";
			String number = "Enter the number coordinate : ";			
			int x = Game.readXAxis(letter); // Call readXAxis
			g.set_X(x);

			int y = Game.readYAxis(number); // Call readYAxis
			g.set_Y(y);
			char c = this.opponentGrid.returnCharacter(y, x);

			if(c == Grid.HIT_MISSED || c == Grid.HIT) {
				same = false;
				System.out.println("You have already hit this position, Enter an other position.");
			}
			else  {
				same = true;

			}
		}
		
	}

}