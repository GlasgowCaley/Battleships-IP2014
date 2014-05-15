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
	// Instantiate 2 players
	private Player []player=new Player[2];

	// Instantiate Guess
	private Guess g;

	// Game method, calls showMenu and kick-starts the game
	public Game() 
	{
		this.g= new Guess(0,0);
		showMenu(); // call showMenu
	}

	// Draw menu
	private void showMenu()
	{	
		System.out.println("\n\tBATTLESHIPS");
		System.out.println("\t-----------\n");

		// Display a numbered list of the user's options
		System.out.println("Please type your choice (1-3)");
		System.out.println("\t1. Start a New Game");
		System.out.println("\t2. View Results");
		System.out.println("\t3. Exit the Game");

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
				System.out.println("Invalid Input"); // Error - try again
				break;

			case 1 :
				newGame(); // Call newGame method
				break;

			case 2 :
				viewResults(); // Call viewResults method
				break;

			case 3 :
				done = true; // If user quits, the loop is done
				quitGame(); // Call quitGame method
				break;
			} 	
		}
	}   

	// Start a new game
	private void newGame()
	{
		// AI or Player?

		// Confirmation message
		System.out.println("New game created!");

		// Instantiate 2 Players and Create ships for both players
		for (int i = 0; i<2; i++){
			Game.clearConsole(); // Clear the screen
			System.out.println("PLAYER "+(i+1)+" enter your name: "); // Player 1/2 - enter your name
			player[i]= new Player();
			System.out.println("Captain " +player[i].name+", it is time to deploy your fleet");
			player[i].addFleet(); // Call addFleet to start placing boats
		}

		// Create opponent grids
		player[0].opponentGrid=player[1].myGrid;
		player[1].opponentGrid=player[0].myGrid;

		// Start to play the game
		this.playGame();

	}

	// Play Game
	private void playGame()
	{
		System.out.println("The game has begun! Good luck!");
		Scanner sc = new Scanner(System.in);  
		int turn=0;
		boolean win=false; // Game is not won
		int x; // x coordinate
		int y; // y coordinate


		do {
			Game.clearConsole(); // Empty the screen		
			System.out.println("Captain " +player[turn].name); // Display name of player whose turn it is
			sc.nextLine();
			System.out.println(display(player[turn].myGrid.displayOwnGrid())); // Show own grid
			System.out.println(display(player[turn].opponentGrid.displayEnnemyGrid())); // Show enemy grid
			System.out.println("Enter your guess:  "); // Ask for guess
			boolean same = false;
			while(!same) {
				// 	Read the guess
				System.out.println("Enter X coordinate :");
				x = Game.readXAxis(); // Call readXAxis
				this.g.set_X(x);

				System.out.println("Enter Y coordinate :");
				y = Game.readYAxis(); // Call readYAxis
				this.g.set_Y(y);
				char c = player[turn].opponentGrid.returnCharacter(y, x);

				if(c == Grid.HIT_MISSED || c == Grid.HIT) {
					same = false;
					System.out.println("You have already hit this position, Enter an other position.");
				}
				else  {
					same = true;
					win=player[turn].makeGuess(g);
				}

			}
			if(!win) turn = (turn+1)%2;
			sc.nextLine();

		} while(!win); // while the game is not won
	}


	// View Results
	private void viewResults()
	{
		char[][] firstPlayerArray = this.player[1].myGrid.displayOwnGrid();
		char[][] secondPlayerArray = this.player[2].myGrid.displayOwnGrid();
		char[][] firstPlayerOpponentArray = this.player[1].myGrid.displayEnnemyGrid();
		char[][] secondPlayerOpponentArray = this.player[2].myGrid.displayEnnemyGrid();
		int nbHitByPlayerOne = 0 ;
		int nbHitByPlayerTwo = 0 ;
		int nbMissByPlayerOne = 0 ;
		int nbMissByPlayerTwo = 0 ;
		int nbShipSunkPlayerOne = 0 ;
		int nbShipSunkPlayerTwo = 0 ;

		//first player stats
		for ( int i = 0 ; i < firstPlayerArray.length;i++){
			for ( int j = 0 ; j < firstPlayerArray[i].length;j++){
				nbShipSunkPlayerOne = 3 - this.player[1].myGrid.getShips().size();

			}
		}

		for ( int i = 0 ; i < firstPlayerOpponentArray.length;i++){
			for ( int j = 0 ; j < firstPlayerOpponentArray[i].length;j++){
				if ( firstPlayerOpponentArray[i][j] == Grid.HIT)
					nbHitByPlayerOne +=1;
				else if ( firstPlayerOpponentArray[i][j] == Grid.HIT_MISSED)
					nbMissByPlayerOne +=1;
			}
		}


		//second player stats
		for ( int i = 0 ; i < secondPlayerArray.length;i++){
			for ( int j = 0 ; j < firstPlayerArray[i].length;j++){
				nbShipSunkPlayerTwo = 3 - this.player[1].myGrid.getShips().size();

			}
		}

		for ( int i = 0 ; i < secondPlayerOpponentArray.length;i++){
			for ( int j = 0 ; j < secondPlayerOpponentArray[i].length;j++){
				if ( secondPlayerOpponentArray[i][j] == Grid.HIT)
					nbHitByPlayerTwo +=1;
				else if ( secondPlayerOpponentArray[i][j] == Grid.HIT_MISSED)
					nbMissByPlayerTwo +=1;
			}
		}

		boolean winnerOrLooser = (this.player[1].myGrid.getShips().size() == 0);
		String winner = "";
		String looser = "";
		if(winnerOrLooser){
			winner = this.player[2].name;
			looser = this.player[1].name;
		}else{			
			winner = this.player[1].name;
			looser = this.player[2].name; 
		}
		

		String res = " Results\n" ;
		res += "\nThe previous game was won by: "+ winner+"\n"; // Should probably use the code above to display winner
		res+= "And the looser is" + looser +"\n";
		
		res += "Stats Player 1\n\n"; 
		res += " Numbers of your shot which hit the opponent : " + nbHitByPlayerOne ;
		res += " \nNumbers of your shot which missed the opponent : " + nbMissByPlayerOne ;
		res += " \nNumbers of your ships which sunk : " + nbShipSunkPlayerOne;


		res += "Stats Player 1\n\n"; 
		res += " \nNumbers of your shot which hit the opponent : " + nbHitByPlayerTwo ;
		res += " \nNumbers of your shot which missed the opponent : " + nbMissByPlayerTwo ;
		res += " \nNumbers of your ships which sunk : " + nbShipSunkPlayerTwo;
		
		System.out.println(res);
	}

	// Quit the Game
	private void quitGame()
	{
		System.out.println("Thanks for playing!");
		System.exit(0);
	}



	/**
	 * Method displaying a two dimensional array into the console.
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

	/**
	 * Method which enables to read the X axis.
	 * @return the integer for the X axis.
	 */
	public static int readXAxis() {
		Scanner sc = new Scanner(System.in);
		String temp = sc.next();
		char c = temp.charAt(0);

		while ((int)c < 65 || (int)c > 74 && (int)c < 97 || (int)c > 106 || temp.length() != 1){
			System.out.println("This is not a valid character");
			temp = sc.nextLine();
			c = temp.charAt(0);
		}		
		int x = Game.changeX(c);
		return x;
	}

	/**
	 * Method which enables to read the Y axis.
	 * @return the integer for the Y axis.
	 */
	public static int readYAxis() {
		Scanner sc = new Scanner(System.in);
		String temp;
		while (!sc.hasNextInt()) {
			System.out.println("invalid! You must enter an integer");
			sc.nextLine();
		}
		temp = sc.next();
		int y =  Integer.parseInt(temp)-1;
		return y;
	}

	private static int changeX(char c){ //The method changes any letter into a number
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

	//public void setGuess() {}

	public static void clearConsole() {
		for(int i = 0; i <25;i++) {
			System.out.println(System.getProperty("line.separator"));
		}
	}

}