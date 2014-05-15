package uk.ac.gcu.battleships.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.gcu.battleships.Ship;
import uk.ac.gcu.battleships.Grid;
import uk.ac.gcu.battleships.Guess;
import uk.ac.gcu.battleships.Player;

public class TestGuess {

	/**
	 * Test the constructor
	 */
	@Test
	public void test_1_Constructor(){
		Guess g = new Guess(1,2);
		
		assertEquals("The guess on the x axis should be 1", 1, g.get_X());
		assertEquals("The guess on the y axis should be 2", 2, g.get_Y());
	}

	/**
	 * Test the Getters
	 */	
	@Test
	public void test_2_GettersTest(){
		Guess g = new Guess(1,2);
		int x = g.get_X();
		int y = g.get_Y();
		
		boolean ok1 = (x==1);
		boolean ok2 = (y==2);
		
		assertTrue("It should be true", ok1);
		assertTrue("it should be true", ok2);
	}
	
	/**
	 * Test the Setters
	 */
	@Test
	public void test_3_SettersTest(){
		Guess g = new Guess(1,2);
		g.set_X(2);
		g.set_Y(1);
		
		assertEquals("The guess on the x axis should be 2",2,g.get_X());
		assertEquals("The guess on the y axis should be 1",1,g.get_Y());
		
	}
}
