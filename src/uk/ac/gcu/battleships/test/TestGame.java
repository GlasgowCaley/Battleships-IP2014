package uk.ac.gcu.battleships.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.gcu.battleships.*;
public class TestGame {

	@Test
	public void testPlayerSize() {
		Player []player = new Player[2];
		int size = 2;
		assertEquals("Equal", player.length, size);
	}
	
	@Test
	public void testGuess() { 
		Guess g = new Guess(0,0);
		assertEquals("Equal", g.get_X(), 0);
		assertEquals("Equal", g.get_Y(), 0);	
	}
	
	@Test
	public void testTurn() {
		int turn = 0;
		assertEquals("Equals", turn, 0);
	}
	
	@Test
	public void testWinFalse() {
		boolean win = false;
		assertEquals("Equal", win, false);
	}
	
	@Test
	public void testSame() {
		boolean same = false;
		assertEquals("Equal", same, false);
		
	}

	@Test
	public void testAllTheHits() {
		int allGuesses[][];
		
		for (int i=0; i < Grid.GRID_SIZE; i++) {
			for (int j=0; j < Grid.GRID_SIZE; j++) {
				Guess guess = new Guess(i, j);
				allGuesses[j][i] = (int)guess.get_X() (int)guess.get_Y();
			}
		}
	}
}
