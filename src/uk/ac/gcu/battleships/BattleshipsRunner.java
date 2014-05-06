package uk.ac.gcu.battleships;

public class BattleshipsRunner {

	public static void main(String[] args) {

		// testing Grid
		Grid testGrid = new Grid(5);
		System.out.println("Character at 3,2 is: " + testGrid.returnCharacter(3, 2) );
		System.out.print(testGrid.toString());
		
		// testing Ship
		Ship testShip = new Ship(3,0,1,'h');
		//bool hit = testShip.isHit(3);
		
		
		System.out.println("Completed test");
		
		

	}

}
