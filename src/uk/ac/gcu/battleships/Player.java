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
         
         temp.length();
         
         

     	//get position of ship
         char c = 0 ;
        System.out.println("Please enter the horizontal(A to E) coordinate of ship " ); //+ count
     	input = new Scanner(System.in);
     	
     	do {
     		System.out.println("You must enter a single character");
     		temp=input.next(); 
     		}while (temp.length() != 1);
     		
     	
     	if ((c < 'a'|| c >'e') && (c <= 'A'|| c >'E' )) {
     		System.out.println("this character is not within the correct range");
     		input.next();
     	}
     		
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
        this.createShip(b,(this.changeX(c)),vert,orient);
         
         
    }
    
    public int changeX(char c){ //The method changes any letter into a number
    	int i=0;
    	boolean enc=false;
    	while(i<=27 && !enc){
    		if(c-'a'==i||c-'A'==i) enc=true;
    		i++;
    	}
    	return i;
    }
    //TESTING
    /*
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