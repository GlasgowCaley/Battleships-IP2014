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
		Ship s2 = new Ship(3,2,2,'h');
		
		
		ok = g.addShip(s);
		boolean ok2 = g.addShip(s2);
		int nb = g.getShips().size();

		// TEST
		assertTrue("The result should be TRUE",ok);
		assertTrue("The result should be TRUE",ok2);
		assertEquals("They should have 2 ships on the grid",2,nb);
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
	
		ok = g.addShip(s);
		boolean ok2 = g.addShip(s2);
		System.out.println(g);
		int nb = g.getShips().size();

		// TEST
		
		assertTrue("The result 1 should be TRUE",ok);
		assertTrue("The result should be TRUE",ok2);
		assertEquals("They should have 2 ships on the grid",2,nb);
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
		Ship s2 = new Ship(2,0,1,'v');

		ok = g.addShip(s);
		boolean ok2 = g.addShip(s2);
		int nb = g.getShips().size();

		// TEST
		assertTrue("The result should be TRUE",ok);
		assertFalse("The result should be FALSE",ok2);
		assertEquals("They should have only a ship on the grid",1,nb);
	}
	
	/** 
	 * Test the checkGuess method.
	 * In this case, the guess hits a ship.
	 */
	@Test
	public void test_8_CheckGuess() {		
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
	public void test_9_CheckGuess_NoHit() {		
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
	
}
