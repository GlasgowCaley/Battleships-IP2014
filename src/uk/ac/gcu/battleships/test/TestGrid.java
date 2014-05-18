package uk.ac.gcu.battleships.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.gcu.battleships.Grid;
import uk.ac.gcu.battleships.Guess;
import uk.ac.gcu.battleships.Ship;

/** 
 * Class for testing the Grid.
 * @author Yann Prono
 * @author Anthony Cerf
 */
public class TestGrid {
	/**
	 * Test the size and contents after the constructor.	
	 */
	@Test
	public void test_1_Constructor() {
		// Initialisation 
		int size = 10;
		Grid g = new Grid(size);

		// TEST FOR SIZE (SQUARE)
		boolean ok = true;
		if(g.getBoard().length != size) {
			ok = false;
		}
		int badSize =size;
		int i = 0;
		while(ok && (i<g.getBoard().length)) {
			if(g.getBoard()[i].length != size) {
				badSize = g.getBoard()[i].length;
				ok = false;
			}
			i++;
		}

		// TEST FOR CONTENT OF GRID
		i = 0;
		boolean charac = true;
		char badChar = '-';
		int j  = 0;
		while(charac && (i<g.getBoard().length)) {
			while(charac && (j<g.getBoard()[i].length)) {
				if(g.getBoard()[i][j] != Grid.DEFAULT_CHAR) {
					badChar = g.getBoard()[i][j];
					charac = false;
				}
				j++;
			}
			i++;
		}

		assertEquals("the Grid should be a square",size,badSize);
		assertEquals("the Grid should contain - in each case of the Grid ",'-',badChar);
	}

	@Test
	public void test_DefaultConstructor() {
		Grid g = new Grid();

		// TEST FOR SIZE (SQUARE)
		boolean ok = true;
		if(g.getBoard().length != 5) {
			ok = false;
		}
		int badSize =5;
		int i = 0;
		while(ok && (i<g.getBoard().length)) {
			if(g.getBoard()[i].length != 5) {
				badSize = g.getBoard()[i].length;
				ok = false;
			}
			i++;
		}

		// TEST FOR CONTENT OF GRID
		i = 0;
		boolean charac = true;
		char badChar = '-';
		int j  = 0;
		while(charac && (i<g.getBoard().length)) {
			while(charac && (j<g.getBoard()[i].length)) {
				if(g.getBoard()[i][j] != Grid.DEFAULT_CHAR) {
					badChar = g.getBoard()[i][j];
					charac = false;
				}
				j++;
			}
			i++;
		}

		assertEquals("the Grid should be a square",5,badSize);
		assertEquals("the Grid should contain - in each case of the Grid ",'-',badChar);

	}

	/** 
	 * Test the returnCharacter() method in normals conditions.
	 */
	@Test
	public void test_2_ReturnCharacter() {
		// Initialisation
		Grid g = new Grid(5);
		int line = 2;
		int row = 3;
		g.getBoard()[line][row] = 'X';

		// TEST
		char res = g.returnCharacter(line, row);
		char res2 = g.returnCharacter(0, 0);

		assertEquals("The Grid should contains X at ["+line+"]["+row+"]",'X',res);		
		assertEquals("The Grid should contains X at [0][0]",'-',res2);
	}

	/** 
	 * Test the returnCharacter method to throw an Exception.
	 */
	@Test
	public void test_3_ReturnCharacterException() {		
		// Initialisation
		Grid g = new Grid(5);
		int row = 3;

		// TEST
		char res = g.returnCharacter(10, row);

		assertEquals("The result should be ' ' (Exception)",0,res);
	}

	/** 
	 * Test the addShip method (horizontal Ship).
	 */
	@Test
	public void test_4_AddShipHorizon() {		
		// Initialisation
		Grid g = new Grid(5);
		boolean ok = false;
		Ship s = new Ship(4,0,0,'h');
		Ship s2 = new Ship(2,2,2,'h');
		Ship s3 = new Ship(2,0,1,'h');



		ok = g.addShip(s);
		boolean ok2 = g.addShip(s2);
		boolean ok3 = g.addShip(s3);
		int nb = g.getShips().size();

		// TEST
		assertTrue("The result should be TRUE",ok);
		assertTrue("The result should be TRUE",ok2);
		assertTrue("The result should be TRUE",ok3);
		assertEquals("They should have 2 ships on the grid",3,nb);
	}

	/** 
	 * Test the addShip method (vertical Ship).
	 */
	@Test
	public void test_5_AddShipVertical() {		
		// Initialisation
		Grid g = new Grid(5);
		boolean ok = false;
		Ship s = new Ship(3,0,0,'v');
		Ship s2 = new Ship(2,1,2,'v');
		Ship s3 = new Ship(2,3,0,'v');

		ok = g.addShip(s);
		boolean ok2 = g.addShip(s2);
		boolean ok3 = g.addShip(s3);

		int nb = g.getShips().size();

		// TEST

		assertTrue("The result 1 should be TRUE",ok);
		assertTrue("The result should be TRUE",ok2);
		assertTrue("The result should be TRUE",ok3);
		assertEquals("They should have 2 ships on the grid",3,nb);
	}

	/** 
	 * Test the addShip method when a Ship is too bigger relative to the Grid.
	 */
	@Test
	public void test_6_AddShip_OutOfBounds() {		
		// Initialisation
		Grid g = new Grid(3);
		boolean ok = false;
		Ship s = new Ship(4,0,0,'h');
		ok = g.addShip(s);
		int nb = g.getShips().size();

		// TEST

		assertTrue("The result should be FALSE",!ok);
		assertEquals("They should no have ships on the grid",0,nb);
	}

	/** 
	 * Test the addShip method with conflicts between two ships.
	 */
	@Test
	public void test_7_AddShip_ConflictShips() {		
		// Initialisation
		Grid g = new Grid(3);
		boolean ok = false;
		Ship s = new Ship(2,0,0,'h');
		Ship s2 = new Ship(2,1,0,'v');

		ok = g.addShip(s);
		boolean ok2 = g.addShip(s2);
		int nb = g.getShips().size();

		// TEST
		assertTrue("The result should be TRUE",ok);
		assertFalse("The result should be FALSE",ok2);
		assertEquals("It should have only a ship on the grid",1,nb);
	}

	/**
	 * Test when the player wants to add a ship Out of the grid
	 */
	@Test
	public void test_8_AddShip_ArrayOutOfBounds(){
		Grid g = new Grid(3);
		Ship s = new Ship(2,5,5,'h');
		boolean ok = g.addShip(s);
		int nb = g.getShips().size();

		//Test
		assertFalse("The result should be false",ok);
		assertEquals("It shouldn't have ship on the grid",0,nb);

	}

	/**
	 * Test when I try to add 3 ship
	 */

	@Test
	public void test_9_AddShip(){
		Grid g = new Grid(10);
		Ship s1 = new Ship(2,0,0,'h');
		Ship s2 = new Ship(3,4,4,'v');
		Ship s3 = new Ship(4,2,8,'h');

		boolean ok1 = g.addShip(s1);
		boolean ok2 = g.addShip(s2);
		boolean ok3 = g.addShip(s3);
		int nb = g.getShips().size();

		//Test
		assertTrue("The result should be true",ok1);
		assertTrue("The result should be true",ok2);
		assertTrue("The result should be true",ok3);
		assertEquals("It should have 3 ships on the grid",3,nb);
	}

	/** 
	 * Test the checkGuess method.
	 * In this case, the guess hits a ship.
	 */
	@Test
	public void test_10_CheckGuess() {		
		// Initialisation
		Grid g = new Grid(5);
		boolean ok = false;
		Ship s = new Ship(2,2,0,'h');
		ok = g.addShip(s);
		Guess gu = new Guess(2,0);
		boolean a = g.checkGuess(gu);
		char r = g.returnCharacter(0,2);

		// TEST
		assertFalse("The result should be FALSE",a);
		assertEquals("The guess shoud hit the ship",Grid.HIT,r);
	}

	/** 
	 * Test the checkGuess method.
	 * In this case, the guess doesn't hit a ship.
	 */
	@Test
	public void test_11_CheckGuess_NoHit() {		
		// Initialisation
		Grid g = new Grid(5);
		boolean ok = false;
		Ship s = new Ship(2,2,0,'h');
		ok = g.addShip(s);

		Guess gu = new Guess(2,1);
		boolean a = g.checkGuess(gu);

		char r = g.returnCharacter(1,2);

		// TEST
		assertFalse("The result should be FALSE",a);
		assertEquals("The guess shouldn't hit the ship",Grid.HIT_MISSED,r);
	}

	/**
	 * Test the displaying of the Own Grid
	 */
	@Test
	public void test_12_displayOwnGrid(){
		Grid gr = new Grid(2);
		boolean ok = true;
		Ship s = new Ship ( 2, 0,0,'v');
		gr.addShip(s);
		char[][] board = gr.displayOwnGrid();
		char[][] array = new char[2][2];
		for ( int i = 0 ; i < 2 ; i++){
			for ( int j = 0; j < 2;j++){
				array[i][j] = Grid.DEFAULT_CHAR;
			}		
		}

		array[0][0] = 'D';
		array[1][0] = 'D';

		for ( int i = 0 ; i < 2 ; i++){
			for ( int j = 0; j < 2;j++){
				if(!(board[i][j] == array[i][j]))
					ok = false;
			}
		}
		assertTrue("It should be true", ok);
	}

	/**
	 * Test the displaying of the ennemy grid
	 */
	@Test
	public void test_13_displayEnnemyGrid(){
		Grid gr = new Grid(2);
		boolean ok = true;
		Ship s = new Ship ( 2, 0,0,'h');
		gr.addShip(s);
		Guess g = new Guess(0,0);
		gr.checkGuess(g);
		char[][] board = gr.displayEnnemyGrid();
		char[][] array = new char[2][2];
		for ( int i = 0 ; i < 2 ; i++){
			for ( int j = 0; j < 2;j++){
				array[i][j] = Grid.DEFAULT_CHAR;
			}		
		}


		array[0][0] = 'x';

		for ( int i = 0 ; i < 2 ; i++){
			for ( int j = 0; j < 2;j++){
				if(!(board[i][j] == array[i][j]))
					ok = false;
			}
		}
		assertTrue("It should be True",ok);
	}

	@Test
	/**
	 * Test if a ship is added on the grid
	 */
	public void test_14_AddShipIntoGrid(){
		Grid g = new Grid(10);
		Ship s = new Ship(2,0,0,'h');
		boolean ok = g.addShip(s);
		char c1 = g.returnCharacter(0, 0);
		char c2 = g.returnCharacter(0, 1);
		char c3 = g.returnCharacter(3,3);

		//Test
		assertTrue("It should be True",ok);
		assertEquals("It should have a ship here",'D',c1);
		assertEquals("It should have a ship here",'D',c2);
		assertEquals("It shouldn't have a ship here",'-',c3);

	}

	@Test
	/**
	 * Test checkGuessAI
	 */

	public void test_15_checkGuessAI(){
		// Initialisation

		Grid g = new Grid(5);

		Ship s = new Ship(2,2,0,'h');
		g.addShip(s);

		Guess gu = new Guess(2,0);
		char a = g.checkGuessAI(gu);

		char r = g.returnCharacter(0,2);

		// TEST
		assertEquals("The result should be h",'h',a);
		assertEquals("The guess should hit the ship",Grid.HIT,r);
	}

	@Test
	/**
	 * Test checkGuessAI if the AI doesn't hit
	 */

	public void test_15_checkGuessAI_NoHit(){
		// Initialisation
		Grid g = new Grid();
		Ship s = new Ship(2,0,0,'h');
		g.addShip(s);

		Guess gu = new Guess(2,2);
		char a = g.checkGuessAI(gu);

		char r = g.returnCharacter(2,2);

		//Test
		assertEquals("The result should be m",'m',a);
		assertEquals("The guess shouldn't hit the ship",Grid.HIT_MISSED,r);

	}

	@Test
	/**
	 * Test checkGuessAI if the AI doesn't hit
	 */

	public void test_15_checkGuessAI_Ship_Sunk(){
		// Initialisation
		Grid g = new Grid();
		Ship s1 = new Ship(2,0,0,'h');
		Ship s2 = new Ship (2,3,3,'h');
		boolean ok1 = g.addShip(s1);
		boolean ok2 = g.addShip(s2);
		
		System.out.println(g);
		Guess gu1 = new Guess(0,0);
		Guess gu2 = new Guess(1,0);
		Guess gu3 = new Guess(3,3);
		Guess gu4 = new Guess(4,3);
		
		
		
		char a1 = g.checkGuessAI(gu1);
		char a2 = g.checkGuessAI(gu2);
		char a3 = g.checkGuessAI(gu3);
		char a4 = g.checkGuessAI(gu4);

		char r1 = g.returnCharacter(0,0);
		char r2 = g.returnCharacter(0,1);
		char r3 = g.returnCharacter(3,3);
		char r4 = g.returnCharacter(3,4);
		
		assertEquals("The result should be h",'h',a1);		
		assertEquals("The result should be s",'s',a2);
		assertEquals("The result should be h",'h',a3);		
		assertEquals("The result should be w",'w',a4);
		assertEquals("The guess should hit the ship",Grid.HIT,r1);
		assertEquals("The guess should hit the ship",Grid.HIT,r2);
		assertEquals("The guess should hit the ship",Grid.HIT,r3);
		assertEquals("The guess should hit the ship",Grid.HIT,r4);

	}
}









