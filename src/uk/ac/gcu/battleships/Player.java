package uk.ac.gcu.battleships;

import java.util.Scanner;

public class Player  {
	public String name;
	public Grid myGrid;
	public Grid opponentGrid;
	private int size;

	public Player(String name){
		char f = name.charAt(0);
		name = Character.toUpperCase(f) + name.substring(1,name.length());
		this.name = name;
		myGrid = new Grid();
		this.setSize(this.myGrid.getBoard().length);
	}

	public boolean createShip(int size, int x, int y, char orientation){
		Ship S = new Ship(size, x, y, orientation); // Create new Ship S
		return myGrid.addShip(S);
	}

	public boolean makeGuess(Guess G){
		PlayerInput p = new PlayerInput();
		p.inputGuess(G, this);
		//this.enterGuess(G);
		return opponentGrid.checkGuess(G);
	}

	public void addFleet(){ // Creates a fleet of boats

		PlayerInput p = new PlayerInput();
		p.inputFleet(this);

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
