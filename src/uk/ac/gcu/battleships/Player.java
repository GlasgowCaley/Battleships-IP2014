package uk.ac.gcu.battleships;

import java.util.Scanner;
import java.io.*;

public class Player  {
	public String name;
	public Grid myGrid;
	public Grid opponentGrid;

	public Player(){
		Scanner sc = new Scanner(System.in);
		name = sc.nextLine();

		myGrid = new Grid();
	}
 
    public boolean createShip(int size, int x, int y, char orientation){
        Ship S = new Ship(size, x, y, orientation);
        return myGrid.addShip(S);
    }
 
     
    public boolean makeGuess(Guess G){
        return opponentGrid.checkGuess(G);
    }
    
    public void addFleet(){
    	
    	String name = "";
    	for(int i=2; i<=4; i++){
    		boolean add = false;
    		switch(i) {
    		case 2:
    			name = "Place your Destroyer (Size 2)";
    			break;
    		case 3:
    			name = "Place your Submarine (Size 3)";
    			break;
    		case 4:
    			name = "Place your Battleship (Size 4)";
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
    
     	//get position of ship
        char c = 0 ;
        System.out.println("Please enter the horizontal(A to E) coordinate of ship " ); //+ count
     	
        int x = Game.readXAxis();
     		
     	System.out.println("Please enter the vertical(1 to 5) coordinate of ship " ); //+ count
     	input = new Scanner(System.in);
     	while (!input.hasNextInt()) {
     		System.out.println("invalid! You must enter an integer");
     		input.next();
     	}
         temp = input.nextLine();
         int vert =  Integer.parseInt(temp)-1;
         
     	//get orientation of ship -- presumably v h
        System.out.println("Please enter the Orientation(v or h) coordinate of ship " ); //+ count
     	input = new Scanner(System.in);
        temp = input.nextLine();
        char orient = temp.charAt(0);
        
        //Not proud of this while loop... Fix into test methods!
        while(temp.length() != 1){
        	System.out.println("That is not a valid orientation ");
        	temp = input.nextLine();
        	orient = temp.charAt(0);
        }
         
     	//call player.createShip method
        add  = this.createShip(b,x,vert,orient);
        return add;         
    }
    
}