package uk.ac.gcu.battleships;

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

	public boolean addShip(Ship boat){		
		return myGrid.addShip(boat);
	}

	public boolean makeGuess(Guess g){
		PlayerInput.inputGuess(g, this);
		return opponentGrid.checkGuess(g);
	}

	public void addFleet(){ // Creates a fleet of boats
		PlayerInput.inputFleet(this);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
