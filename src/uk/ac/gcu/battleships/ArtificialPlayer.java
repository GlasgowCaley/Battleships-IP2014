package uk.ac.gcu.battleships;

import java.util.Random;
import java.util.Scanner;

public class ArtificialPlayer extends Player{

	//private boolean hitBefore, hitLine, hitRow;
	//private Guess lastHit;


	public ArtificialPlayer(){

		/*hitBefore=false;
		hitLine=false;
		hitRow=false;
		lastHit = new Guess(0,0);*/		
	}

	public void inputName() 
	{
		this.name = "Computer";
	}


	public boolean makeGuess(Guess g){  	
		boolean resul;

		/*if(!hitBefore){
    			G.set_X((int)Math.random()*5);
    			G.set_Y((int)Math.random()*5);

    		}
    		else if (hitLine){

    		}
    		else if(hitRow){

    		}
    		else {
    			G.set_X((int)Math.random()*5);
    			G.set_Y((int)Math.random()*5);//Math.random()*(N-M+1)+M
    										//N=lasHit.get_X-1

    		}
		 */
		//By now the computer only do randomly movements. We improve that
		int x = (int)(Math.random()*5);
		int y = (int)(Math.random()*5);
		g.set_X(x);
		g.set_Y(y);


		resul=opponentGrid.checkGuess(g);

		//hitBefore = (this.opponentGrid.returnCharacter(G.get_X(), G.get_Y()) == Grid.HIT);
		//if (hitBefore) lastHit=G;

		return resul;
	}

	public void addFleet(){ // Creates a fleet of boats  
		boolean add=false;
		for(int i=2; i<=4; i++){    		
			while(!add) {
				add = this.addBoat(i);
			}
			add = false;
		}
	}

	public boolean addBoat(int b){
		boolean add= false;
		int x = (int) (Math.random()*5);
		int y = (int) (Math.random()*5);
		char orient;
		boolean orien = new Random().nextBoolean();
		if (orien) 
			orient='h';
		else 
			orient='v';
		add  = this.createShip(b,x,y,orient);
		return add;
	}

	public boolean enterGuess(Guess g) {
		return this.makeGuess(g);
	}



}
