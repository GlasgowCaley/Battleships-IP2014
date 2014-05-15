package uk.ac.gcu.battleships;

import java.util.Scanner;
/**
 * Game Class
 * Displays a menu
 * Accepts input from user
 * @author Robert Morrison
 * @author Fiona Harris
 * @version 1.0
 * 
 */

public class Game
{
    /**
    * Creates an object of type Game
    * Calls method showMenu
    */
	private Player []player=new Player[2];
	
    public Game() 
    {
        showMenu();
    }

    /**
    * Creates a menu
    * If user enters 1, call newGame method
    * If user enters 2, call playGame method
    * If user enters 3, call viewResults method
    * If user enters 4, call quitGame method
    */
    private void showMenu()
    {
        // Display a numbered list of the user's options
        System.out.println("1. New Game");
        System.out.println("2. Play Game");
        System.out.println("3. View Results");
        System.out.println("4. Quit");
        System.out.println("Please indicate your choice by typing the appropriate number");
        
        // Get the user's choice
        Scanner choice = new Scanner(System.in);
        
        // Carry out appropriate method relating to user choice
        boolean done = false; // Loop is not finished
        while (!done) {
            while(!choice.hasNextInt()) { // While user enters invalid input
                System.out.println("Invalid input");
                choice.next();
            }
            int i = choice.nextInt(); // Save the user's choice as int i
            
            // Call the appropriate method
            
            switch(i) {
            
            	default :
            		System.out.println("Invalid Input");
            	break;
            	
            	case 1 :
            		newGame(); // Call newGame method
            	break;
            	
            	case 2 :
            		playGame(); // Call playGame method
            	break;
            	
            	case 3 :
            		viewResults(); // Call viewResults method
            	break;
            	
            	case 4 :
            		done = true; // If user quits, the loop is done
            		quitGame(); // Call quitGame method
            	break;
            	
            } 	
            
        }
    }   

    /**
    * Starts a new game
    * Get input relating to size & position of ships
    */
    private void newGame()
    {
    	// Confirmation message
        System.out.println("New game created");
        
        // Instantiate 2 Players and Create ships for both players
        for (int i = 0; i<2; i++){
            System.out.println("PLAYER "+i+" enter your name: ");
        	player[i]= new Player();
            System.out.println("Captain " +player[i].name+" Deploy your fleet:");
        	player[i].addFleet();
        }
        
        // Create opponent grids
        player[0].opponentGrid=player[1].myGrid;
        player[1].opponentGrid=player[0].myGrid;
        
        this.playGame();
        		
    }

    /**
    * Begins the existing game
    * In version 1.0 this will simply return a string
    */
    private void playGame()
    {
        System.out.println("You are playing the game. Honestly.");
        Scanner sc = new Scanner(System.in);  
        int turn=0;
        Guess g= new Guess(0,0);
        boolean win=false;
        String x;
        int y;
        
        do{
        	System.out.println("Captain " +player[turn].name);        	
        	display(player[turn].myGrid.displayOwnGrid());
        	display(player[turn].opponentGrid.displayEnnemyGrid());
            System.out.println("Enter your guess:  ");
            //Read the guess
            x = sc.next();
            y = sc.nextInt();
            g.set_X(x.charAt(0));
            g.set_Y(y);
            win=player[turn].makeGuess(g);
            
            if(!win) turn = (turn+1)%2;

        }while(!win);
        //loop for sunk!=3
        //turn = 0
        //display grids
        //Player1 make a guess : read the guess and calls makeGuess
        //display result of guess
        //turn = (turn+1)%2
        //display player 2 grid
        //player2 guess
        //display result turn%2

    }

    /**
    * Shows results of the game
    * In version 1.0 this will simply return a string
    */
    private void viewResults()
    {
        System.out.println("View results");
    }
    
    /**
     * Quits the game
     */
    private void quitGame()
    {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    

/**
 * Method displaying a two dimensionnal array into the console.
 */
public String display(char[][] array){
	int k = 4;
	String format = "%-3s";
	String limit = "    +"+new String(new char[array.length]).replace("\0","---+");
	//StringBuffer r =new StringBuffer("    ");
	String deca = new String(new char[k]).replace("\0"," ");
	String res = deca;
	char row = 'A';
	int line = 1;

	//loop to display row letter
	for(int i = 0; i<array.length;i++){
		res+= "  "+row+" ";	 	 
		row++;
	}
	res+= System.getProperty("line.separator");
	res+= limit;

	//loop to display line number and each character
	for(int i = 0; i<array.length;i++){
		res+= System.getProperty("line.separator");
		res += " "+String.format(format, line);
		
		for(int j = 0; j<array[i].length;j++) {
			res+= "| ";
			res += array[i][j]+" ";
		}
		res+= "|";
		res+= System.getProperty("line.separator");
		res+= limit;
		line++;
	}

	// Word wrap
	res+= System.getProperty("line.separator");

	return res;
}


}