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
        
        // Instantiate 2 Players
        Player player1 = new Player();
        Player player2 = new Player();
        
        // Create ships for both players
        System.out.println("Player 1 please place your ships");
        player1.addFleet();
        System.out.println("Player 2 please place your ships");
        player2.addFleet();
        
        // Create opponent grids
        player1.opponentGrid=player2.myGrid;
        player2.opponentGrid=player1.myGrid;
        		
    }

    /**
    * Begins the existing game
    * In version 1.0 this will simply return a string
    */
    private void playGame()
    {
        System.out.println("You are playing the game. Honestly.");
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

    
}

