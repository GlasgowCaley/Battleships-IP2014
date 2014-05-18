package uk.ac.gcu.battleships;
import java.util.Scanner;

/**
 * Game Class
 * Displays a menu
 * Accepts input from user
 * @author Robert Morrison
 * @author Fiona Harris
 * @version 1.x
 * 
 */

public class Game
{
	/** Instantiates 2 players */
	private Player []player=new Player[2];

	/** Instantiate Guess */
	private Guess g;

	/** 
	 * Game Method
	 * Shows the menu
	 * Starts the game
	 * Controls the flow of the game
	 */
	public Game() 
	{
		this.g= new Guess(0,0);
		showMenu(); // call showMenu
	}

	/**
	 * showMenu method
	 */
	private void showMenu()
	{	
		/** Prints "Battleships" in Kick-Ass ASCII Art */
		System.out.println("  ____       _______ _______ _      ______  _____ _    _ _____ _____   _____ _ \r\n |  _ \\   /\\|__   __|__   __| |    |  ____|/ ____| |  | |_   _|  __ \\ / ____| |\r\n | |_) | /  \\  | |     | |  | |    | |__  | (___ | |__| | | | | |__) | (___ | |\r\n |  _ < / /\\ \\ | |     | |  | |    |  __|  \\___ \\|  __  | | | |  ___/ \\___ \\| |\r\n | |_) / ____ \\| |     | |  | |____| |____ ____) | |  | |_| |_| |     ____) |_|\r\n |____/_/    \\_\\_|     |_|  |______|______|_____/|_|  |_|_____|_|    |_____/(_)\r\n                                                                               \r\n                                                                               ");
		/** Displays the menu */
		System.out.println("Please type your choice (1-3)");
		System.out.println("1. Start a New Game");
		System.out.println("2. View Results");
		System.out.println("3. Exit the Game");

		/** Get the user's choice */
		Scanner choice = new Scanner(System.in);

		/** Take the user's choice, ensuring it is a valid integer */
		boolean done = false; 								// Loop is not finished
		while (!done) {
			while(!choice.hasNextInt()) { 					// While user enters invalid input
				System.out.println("Invalid input");
				choice.next();
			}
			int i = choice.nextInt(); 						// Save the user's choice as int i

		/** Ensure that the integer is a valid option and call the correct method */
			switch(i) {

			default :
				System.out.println("Invalid Input"); 		// Error - try again
				break;

			case 1 :
				newGame(); 									// Call newGame method
				break;

			case 2 :
				viewResults(); 								// Call viewResults method
				break;

			case 3 :
				done = true; 								// If user quits, the loop is done
				quitGame(); 								// Call quitGame method
				break;
			} 	
		}
	}   

	/** newGame method
	 * Starts a new game
	 * Player can choose between playing against another person or against the AI
	 */
	private void newGame()
	{
		/** Choose mode - Player vs Player or Player vs Computer */
		System.out.println("Press Y to play against the AI or press any other key to play against a human");		
		Scanner sc = new Scanner(System.in);
		String yn = sc.nextLine();							// Save entire input to String yn
		char c = yn.toUpperCase().charAt(0);				// Convert string to upper-case Char
		int nb = 2;											// nb stores the number of human players - 2 by default	

		switch(c) {
		case 'Y':											// If user enters 'Y'
			player[1] = new ArtificialPlayer();				// Create a new AI Player
			player[1].addFleet();							// Add the AI's ships
			System.out.println(player[1].myGrid);			// Display Grid
			nb = 1;
			break;
		}

		/** Instantiate 2 Players and their ships */
		for (int i = 0; i<nb; i++){
			Game.clearConsole(); 										// Clear the screen
			System.out.print("Player "+(i+1)+", enter your name: "); 	// Player 1/2 - enter your name
			player[i]= new Player();

			if(nb == 2) 												// If there are two human players
				System.out.println("Captain " +player[i].name+", it is time to deploy your fleet\n");

			player[i].addFleet(); 										// Call addFleet to start placing boats
		}
		System.out.println(player[1].myGrid);
		
		/** Create "opponent" versions of each grid to display hits and misses only */
		player[0].opponentGrid=player[1].myGrid;
		player[1].opponentGrid=player[0].myGrid;

		/** Start the game by calling the playGame method */
		this.playGame();

	}


	/** Play the game */
	private void playGame()
	{
		System.out.println("The game has begun! Good luck!");
		Scanner sc = new Scanner(System.in);  
		int turn=0;
		boolean win=false; 									// Game is not won by default

		do {
			win = player[turn].makeGuess(g);
			char res  = player[turn].opponentGrid.returnCharacter(g.get_Y(), g.get_X());
			if( res == Grid.HIT ) {
				switch(player[turn].name) {
				case "Computer" :
					System.out.println("One of your ships has been hit!");
					break;
				default :
					System.out.println("You have hit an enemy ship!");
				}				
			}
			if(!win) turn = (turn+1)%2;
			sc.nextLine();
		}
		while(!win); // while the game is not won
		System.out.println(player[turn].name+" wins the game!");
		this.viewResults();		
	}

	/** View the results and stats of the previous game */

	private void viewResults()
	{
		try {
			char[][] firstPlayerOpponentArray = this.player[1].myGrid.displayEnnemyGrid();
			char[][] secondPlayerOpponentArray = this.player[0].myGrid.displayEnnemyGrid();
			int nbHitByPlayerOne = 0 ;
			int nbHitByPlayerTwo = 0 ;
			int nbMissByPlayerOne = 0 ;
			int nbMissByPlayerTwo = 0 ;
			int nbShipSunkPlayerOne = 0 ;
			int nbShipSunkPlayerTwo = 0 ;

			/** Calculate Player 1 stats */
			for( int k = 0 ;k < this.player[0].myGrid.getShips().size() ; k++){
				if (this.player[0].myGrid.getShips().get(k).isSunk())
					nbShipSunkPlayerOne ++;
			}

			for ( int i = 0 ; i < firstPlayerOpponentArray.length;i++){
				for ( int j = 0 ; j < firstPlayerOpponentArray[i].length;j++){
					if ( firstPlayerOpponentArray[i][j] == Grid.HIT)
						nbHitByPlayerOne +=1;
					else if ( firstPlayerOpponentArray[i][j] == Grid.HIT_MISSED)
						nbMissByPlayerOne +=1;
				}
			}


			/** Calculate Player 2 stats */
			for( int k = 0 ;k < this.player[1].myGrid.getShips().size() ; k++){
				if (this.player[1].myGrid.getShips().get(k).isSunk())
					nbShipSunkPlayerTwo ++;
			}

			for ( int i = 0 ; i < secondPlayerOpponentArray.length;i++){
				for ( int j = 0 ; j < secondPlayerOpponentArray[i].length;j++){
					if ( secondPlayerOpponentArray[i][j] == Grid.HIT)
						nbHitByPlayerTwo +=1;
					else if ( secondPlayerOpponentArray[i][j] == Grid.HIT_MISSED)
						nbMissByPlayerTwo +=1;
				}
			}

			boolean winnerOrLoser = (this.player[0].myGrid.getShips().size() == 0);
			String winner = "";
			String loser = "";
			if(winnerOrLoser){
				winner = this.player[1].name;
				loser = this.player[0].name;
			}else{			
				winner = this.player[0].name;
				loser = this.player[1].name; 
			}

			String res = "\n --- Results ---\n" ;
			res += "\nThe previous game was won by: "+ winner+"\n"; // Should probably use the code above to display winner
			res+= "And the loser is: " + loser +"\n\n";

			if(!winnerOrLoser){
				res += winner +"\n\n"; 
				res += "\nNumber of Hits: " + nbHitByPlayerOne ;
				res += "\nNumber of Misses: " + nbMissByPlayerOne ;
				res += "\nNumber of Ships Sunk: " + nbShipSunkPlayerOne+"\n\n";


				res += loser+"\n\n"; 
				res += "\nNumber of Hits: " + nbHitByPlayerTwo ;
				res += "\nNumber of Misses: " + nbMissByPlayerTwo ;
				res += "\nNumber of Ships Sunk: " + nbShipSunkPlayerTwo;
			}else{
				res += winner+"\n\n"; 
				res += "\nNumber of Hits: " + nbHitByPlayerTwo ;
				res += "\nNumber of Misses: " + nbMissByPlayerTwo ;
				res += "\nNumber of Ships Sunk: " + nbShipSunkPlayerTwo;


				res += loser+"\n\n"; 
				res += " \nNumber of Hits: " + nbHitByPlayerOne ;
				res += " \nNumber of Misses: " + nbMissByPlayerOne ;
				res += " \nNumber of Ships Sunk:  " + nbShipSunkPlayerOne;
			}

			System.out.println(res);
		}
		catch (NullPointerException e) {
			System.out.println("You have to play before!");
		}
	}


	/** Quit the game */
	private void quitGame()
	{
		System.out.println("Thanks for playing!");
		System.exit(0);
	}

	/**
	 * Method displaying a two dimensional array into the console.
	 */
	public static String display(char[][] array){
		int k = 4;
		String format = "%-3s";
		String limit = "    +"+new String(new char[array.length]).replace("\0","---+");
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

	/**
	 * Method which enables to read the X axis.
	 * @return the integer for the X axis.
	 */
	
	public static int[] readCoordinate(String msg,int m) {
		int[] coord = new int[2];
		Scanner sc = new Scanner(System.in);
		String temp = "";		
		char limit  = (char)(((int)'A')+m-1);
		String regex = "[A-"+ limit +"][1-"+m+"]";
		boolean respect = false;
		char x = 0;
		int y = 0;
		while(!respect) {
			System.out.println(msg);
			temp = sc.next();
			temp = temp.toUpperCase();
			respect = temp.matches(regex);
			if(respect) {
				x = temp.charAt(0);
				y =Character.getNumericValue(temp.charAt(1));
			} 
		}				
		coord[0] = Game.changeX(x);
		coord[1] = y-1;
		return coord;
	}
	
	public static char readOrientation(String msg) {
		Scanner sc = new Scanner(System.in);
		String temp = "";
		char c = 0;
		boolean ok = false;
		while (!ok){			
			System.out.println(msg);
			temp = sc.next();
			c = temp.toUpperCase().charAt(0);
			ok = (c == 'V' || c == 'H');			
		}		
		return c;		
	}

	public static int changeX(char c){ //The method changes any letter into a number
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

	public static void clearConsole() {
		for(int i = 0; i <25;i++) {
			System.out.println(System.getProperty("line.separator"));
		}
	}

}