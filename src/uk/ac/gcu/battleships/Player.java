package uk.ac.gcu.battleships;

import java.util.Scanner;
import java.io.*;

public class Player  {
	public String name;
	public Grid myGrid;
	public Grid opponentGrid;

	public Player(){
		Scanner sc = new Scanner(System.in);
		name = sc.nextLine(); // Save name of the player
		myGrid = new Grid();
	}
 
    public boolean createShip(int size, int x, int y, char orientation){
        Ship S = new Ship(size, x, y, orientation); // Create new Ship S
        return myGrid.addShip(S);
    }
 
     
    public boolean makeGuess(Guess G){
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
    			System.out.println(name);
    			add = this.addBoat(i);
    			System.out.println(this.myGrid);
    		}    		
    	}
    }
    
    public boolean addBoat(int b){
    	 Scanner input = new Scanner(System.in);
         String temp;
         boolean add = false;
    
     	// Get starting position of ship
        char c = 0 ;
        System.out.println("Please enter the horizontal (A to E) coordinate of ship " ); //+ count
     	int x = Game.readXAxis(); // Get X Axis value, save to x
     		
     	System.out.println("Please enter the vertical (1 to 5) coordinate of ship " ); //+ count
     	int y = Game.readYAxis(); // Get Y Axis value, save to y
         
     	// Get orientation of ship - Horizontal or Vertical
        System.out.println("Please enter the orientation of the ship (H or V)" ); //+ count
     	input = new Scanner(System.in);
        temp = input.nextLine(); // 
        char orient = temp.toUpperCase().charAt(0);
        System.out.println(orient);
        
        //Not proud of this while loop... Fix into test methods!

        while((orient != 'H' && orient != 'V') || (temp.length() != 1)) {
        	System.out.println("That is not a valid orientation ");
        	System.out.println("Please enter the orientation of the ship (H or V)" ); //+ count
        	temp = input.nextLine();
        	orient = temp.toUpperCase().charAt(0); // change lower case v/h to upper case V/H
        }
         
     	//call player.createShip method
        add  = this.createShip(b,x,y,orient);
        return add;
    }
   

}

