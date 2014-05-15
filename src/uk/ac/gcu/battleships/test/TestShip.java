package uk.ac.gcu.battleships.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.gcu.battleships.Ship;
import uk.ac.gcu.battleships.Grid;
import uk.ac.gcu.battleships.Guess;
import uk.ac.gcu.battleships.Player;

public class TestShip {

	/**
	 * Test the constructor
	 */
	@Test
	public void test_1_Constructor() {
		Ship s = new Ship(2,0,0,'h');
		int size = s.shipSize;
		int x = s.shipPosition_x;
		int y = s.shipPosition_y;
		char orient = s.shipOrientation;

		assertEquals("The size should be 2",2,size);
		assertEquals("The x axis should be 0",0,x);
		assertEquals("The y axis should be 0",0,y);
		assertEquals("The orientation should be horizontal",'h',orient );

	}

	
}
