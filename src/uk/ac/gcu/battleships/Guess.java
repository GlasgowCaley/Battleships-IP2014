package uk.ac.gcu.battleships;

public class Guess {
	private int x_coord, y_coord;
	
	//CONSTRUCTORS
	
	public Guess(int x, int y){
		x_coord=x;
		y_coord=y;
	}
	//METHODS
	public int get_X(){
		return x_coord;
	}
	
	public int get_Y(){
		return y_coord;
	}
}