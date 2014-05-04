package uk.ac.gcu.battleships;

public class Player  {
	public String name;
	public Grid myGrid;
	
	public Player(){
		myGrid = new Grid;
	}
	
	public void createShip(int x, int y, int size, char orientation){
		Ship S = new Ship;
		S.setPosition(x,y);
		S.setShipSize(size);
		S.setShipOrientation(orientation);
		myGrid.addShip(S);
	}
	
	public boolean makeGuess(Guess G){
		return(G.get_X()==2&&G.get_Y()==2);
	}
	
	//TESTING
public static void main(String[] args) {
		Player p1 = new Player();	
		Guess g1 = new Guess((byte)2,(byte)2);					
		Guess g2 = new Guess((byte)2,(byte)1);					
		System.out.println(p1.makeGuess(g1));
		System.out.println(p1.makeGuess(g2));					
	}
}
