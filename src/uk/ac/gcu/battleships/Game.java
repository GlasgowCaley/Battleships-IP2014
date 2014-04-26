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
            while(!choice.hasNextInt()) { // While user enters a valid integer into the Scanner
                System.out.println("Invalid input");
                choice.next();
            }
            int i = choice.nextInt(); // Save the user's choice as int i
            
            // Call the appropriate method
            // I think I might change this to a switch statement in the next version
            if (i == 1) {
                newGame(); // Call newGame method
            }
            
            else if (i == 2) {
                playGame(); // Call playGame method
            }
            
            else if (i == 3) {
                viewResults(); // Call viewResults method
            }
            
            else if (i == 4) {
                done = true; // If user quits, the loop is done
                quitGame(); // Call quitGame method
            }
            
            else { 
                System.out.println("Invalid input");
            }
        }
    }   

    /**
    * Starts a new game
    * In version 1.0 this will simply return a string
    */
    private void newGame()
    {
        System.out.println("New game created, but you can't play it yet.");
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