package uk.ac.gcu.battleships;

import java.util.Scanner;
import java.io.*;

public class Player  {
	public String name;
	public Grid myGrid;
	public Grid opponentGrid;

	public Player(){
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
    	
    	for(int i=2; i<=4; i++){
    		if (i==2) System.out.println("DESTROYER: " );
    			else if (i==3)  System.out.println("SUBMARINE: " );
    				else System.out.println("BATTLESHIP: " );
    		this.addBoat(i);
    	} 	
    }
    
    public void addBoat(int b){
    	 Scanner input = new Scanner(System.in);
         String temp = input.nextLine();
    
     	//get position of ship
        char c = 0 ;
        System.out.println("Please enter the horizontal(A to E) coordinate of ship " ); //+ count
     	input = new Scanner(System.in);
     	
     	temp = input.next(); 
     	
     	c = temp.charAt(0);
     	
     	while ((int)c < 65 || (int)c > 74 && (int)c < 97 || (int)c > 106 || temp.length() != 1){
     		System.out.println("This is not a valid character");
     		temp = input.next();
     		c = temp.charAt(0);
     	}
     		
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
         
     	//call player.createShip method
        this.createShip(b,(this.changeX(c)),vert,orient);
         
         
    }
    
    public int changeX(char c){ //The method changes any letter into a number
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
    //TESTING
    /*
=======
	public Player(String n){
		name = n;
	}

	public Player(String n, int size){
		name = n;
		myGrid = new Grid(size);
	}


	public boolean createShip(int size, int x, int y, char orientation){
		Ship S = new Ship(size, x, y, orientation);
		return myGrid.addShip(S);
	}


	public boolean makeGuess(Guess G){
		return opponentGrid.checkGuess(G);
	}

	public void addFleet(){

		for(int i=2; i<=4; i++){
			if (i==2) System.out.println("DESTROYER: " );
			else if (i==3)  System.out.println("SUBMARINE: " );
			else System.out.println("BATTLESHIP: " );
			this.addBoat(i);
		}


	}
	public void addBoat(int b){
		Scanner input = new Scanner(System.in);
		String temp = input.nextLine();


		//get position of ship
		System.out.println("Please enter the horizontal(A to E) coordinate of ship " ); //+ count
		input = new Scanner(System.in);
		while (!input.hasNextInt()) {
			System.out.println("invalid! You must enter a letter");
			input.next();
		}
		temp = input.nextLine();
		char horiz =  (char) Integer.parseInt(temp); //The player inputs a character for the x coordinates 

		System.out.println("Please enter the vertical(1 to 5) coordinate of ship " ); //+ count
		input = new Scanner(System.in);
		while (!input.hasNextInt()) {
			System.out.println("invalid! You must enter an integer");
			input.next();
		}
		temp = input.nextLine();
		int vert =  Integer.parseInt(temp);

		//get orientation of ship -- presumably v h
		System.out.println("Please enter the Orientation(v or h) coordinate of ship " ); //+ count
		input = new Scanner(System.in);
		temp = input.nextLine();
		char orient = temp.charAt(0);

		//call player.createShip method
		this.createShip(b,changeX(horiz),vert,orient);


	}

	public int changeX(char c){ //The method changes any letter into a number
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


	//TESTING
	/*
>>>>>>> 2e31d5d9f1cef45837f2fa7ed303f971d4d66815
public static void main(String[] args) {
        Game game = new Game();
        Player p1 = new Player();   
        Guess g1 = new Guess((byte)2,(byte)2);                  
        Guess g2 = new Guess((byte)2,(byte)1);                  
        System.out.println(p1.makeGuess(g1));
        System.out.println(p1.makeGuess(g2));                   
    }
	 */
}