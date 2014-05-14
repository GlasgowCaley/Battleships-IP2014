package uk.ac.gcu.battleships;

import java.io.*;
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
    // We need to do input validation
    // We need to fix the A-J thing
    {
        System.out.println("New game created");
        //create two players
        
        //player 1
        Player player1 = new Player();
        
    		//get size of ship
	        System.out.println("Player 1 Please enter the size of ship " ); //+ count
	        Scanner input = new Scanner(System.in); // save entire user input
	        while (!input.hasNextInt()) { // input validation. While there is no int in input...
	        	System.out.println("Player 1 Invalid! You must enter an integer");
	        	input.next(); // next input
	        }
	        String temp = input.nextLine(); // save current line of user input in temp
	        int p1Size = Integer.parseInt(temp); // look for integer in temp. Save int to p1size
            
        	//get position of ship
        	System.out.println("Player 1 Please enter the horizontal(1 to 10) coordinate of ship " ); //+ count
        	input = new Scanner(System.in);
        	while (!input.hasNextInt()) {
        		System.out.println("invalid! You must enter an integer");
        		input.next();
        	}
            temp = input.nextLine();
            int p1Horiz =  Integer.parseInt(temp);
            
        	System.out.println("Player 1 Please enter the vertical(1 to 10) coordinate of ship " ); //+ count
        	input = new Scanner(System.in);
        	while (!input.hasNextInt()) {
        		System.out.println("invalid! You must enter an integer");
        		input.next();
        	}
            temp = input.nextLine();
            int p1Vert =  Integer.parseInt(temp);
            
        	//get orientation of ship -- presumably v h
            System.out.println("Player 1 Please enter the Orientation(v or h) coordinate of ship " ); //+ count
        	input = new Scanner(System.in);
            temp = input.nextLine();
            char p1Orient = temp.charAt(0);
            
        	//call player.createShip method
            player1.createShip(p1Size,p1Horiz,p1Vert,p1Orient);
        
        //player 2
            Player player2 = new Player();
        		//get size of ship
            	System.out.println("Player 2 Please enter the size of ship " ); //+ count
                input = new Scanner(System.in);
                while (!input.hasNextInt()) {
            		System.out.println("invalid! You must enter an integer");
            		input.next();
            	}
                temp = input.nextLine();
                int p2Size = Integer.parseInt(temp);

            	//get position of ship
            	System.out.println("Player 2 Please enter the horizontal(1 to 10) coordinate of ship " ); //+ count
            	input = new Scanner(System.in);
            	while (!input.hasNextInt()) {
            		System.out.println("invalid! You must enter an integer");
            		input.next();
            	}
                temp = input.nextLine();
                int p2Horiz =  Integer.parseInt(temp);
                
            	System.out.println("Player 2 Please enter the vertical(1 to 10) coordinate of ship " ); //+ count
            	input = new Scanner(System.in);
            	while (!input.hasNextInt()) {
            		System.out.println("invalid! You must enter an integer");
            		input.next();
            	}
                temp = input.nextLine();
                int p2Vert =  Integer.parseInt(temp);
                
            	//get orientation of ship -- presumably v h
                System.out.println("Player 2 Please enter the Orientation(v or h) coordinate of ship " ); //+ count
            	input = new Scanner(System.in);
                temp = input.nextLine();
                char p2Orient = temp.charAt(0);
                
            	//call player.createShip method
                player2.createShip(p2Size,p2Horiz,p2Vert,p2Orient);
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

