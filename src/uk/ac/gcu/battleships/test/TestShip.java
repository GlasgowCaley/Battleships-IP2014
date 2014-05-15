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
	
	@Test
	public void test_2_shipHit(){
		Ship s = new Ship(2, 0 ,0 ,'v');
		assertTrue("This should be true", s.testHit(0, 0));
		assertTrue("This should be true", s.testHit(0, 1));
		assertFalse("This should be false", s.testHit(3, 4));
		assertFalse("This should be false", s.testHit(1, 0));
	}

	@Test
	public void test_3_sinking(){
		Ship s = new Ship(3,0,0,'h');
		assertTrue("This should be true", s.testHit(0, 0));
		assertFalse("This should be false", s.isSunk());
		assertTrue("This should be true", s.testHit(1, 0));
		assertTrue("This should be true", s.testHit(2, 0));
		assertTrue("This should be true", s.isSunk());
		
	}
	
	@Test
	public void test_4_isHit(){	//hit vertical ship
		Ship s = new Ship(3,0,0,'h');
		s.testHit(1, 0);
		assertFalse("This should be false", s.isHit(0));
		assertTrue("This should be true", s.isHit(1));
		assertFalse("This should be false", s.isHit(2));
	}
	
	@Test
	public void test_5_isHit(){
		Ship s = new Ship(3,0,0,'v');
		assertFalse("This should be false", s.testHit(1, 0));
		assertTrue("This should be true", s.testHit(0, 1));
		
		assertTrue("This should be true", s.isHit(1));
		assertFalse("This should be false", s.isHit(0));
		
	}
}




