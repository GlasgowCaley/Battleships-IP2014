package uk.ac.gcu.battleships;

import java.util.Random;
import java.util.Scanner;

public class ArtificialPlayer extends Player{

	private boolean hitBefore, hitLine, hitRow, back;
	private Guess lastHit;
	private int r,l,rl;


	public ArtificialPlayer(){

		myGrid = new Grid();
		hitBefore=false;
		hitLine=false;
		hitRow=false;
		back = false;
		lastHit = new Guess(0,0);
		r=(int) Math.floor(Math.random()*1);
		l=(int) Math.floor(Math.random()*1);
		rl=(int) Math.floor(Math.random()*3);		
	}

	public void inputName() 
	{
		this.name = "Computer";
	}
	

	public boolean makeGuess(Guess G){
		char resul;
		do{
			if(!hitBefore){
				G.set_X((int)(Math.random()*4));
				G.set_Y((int)(Math.random()*4));
			}

			else if(hitLine)
			{
				switch(l){
				case 0:
					G.set_Y(lastHit.get_Y()-1);
					G.set_X(lastHit.get_X());
					break;
				case 1:
					G.set_Y(lastHit.get_Y()+1);
					G.set_X(lastHit.get_X());
					break;
				}
			}
			else if(hitRow)
			{
				switch(r){
				case 0:
					G.set_X(lastHit.get_X()-1);
					G.set_Y(lastHit.get_Y());
					break;
				case 1:

					G.set_X(lastHit.get_X()+1);
					G.set_Y(lastHit.get_Y());
					break;
				}
			}

			else
			{
				switch(rl){
				case 0:
					G.set_Y(lastHit.get_Y()-1);
					G.set_X(lastHit.get_X());
					break;

				case 1:
					G.set_X(lastHit.get_X()+1);
					G.set_Y(lastHit.get_Y());
					break;

				case 2:
					G.set_Y(lastHit.get_Y()+1);
					G.set_X(lastHit.get_X());
					break;

				case 3:
					G.set_X(lastHit.get_X()-1);
					G.set_Y(lastHit.get_Y());
					break;
				}
				rl = (rl+1)%4;
			}
			//By now the computer only do randomly movements. We improve that
			//G.set_X((int)Math.random()*5);
			//G.set_Y((int)Math.random()*5);
		}while(!(this.opponentGrid.returnCharacter(G.get_X(), G.get_Y()) == Grid.DEFAULT_CHAR) && !(this.opponentGrid.returnCharacter(G.get_X(), G.get_Y()) == 's') && !(this.opponentGrid.returnCharacter(G.get_X(), G.get_Y()) == 'd') && !(this.opponentGrid.returnCharacter(G.get_X(), G.get_Y()) == 'b'));

		resul=opponentGrid.checkGuessAI(G);

		switch(resul){
		case 'h':
			if(hitBefore)
			{
				if(rl == 1 || rl == 3)
					hitRow = true;
				else
					hitLine = true;
			}
			else 
				hitBefore = true;
			break;

		case 's':
			hitBefore = false;
			hitLine = false;
			hitRow = false;
			back = false;
			break;
		case 'm':
			if (hitLine){
				if (back){
					hitBefore = false;
					hitLine = false;
					hitRow = false;
					back = false;
				}
				else{
					l=(l+1)%2;
					back = true;
				}
			}
			if (hitRow){
				if (back){
					hitBefore = false;
					hitLine = false;
					hitRow = false;
					back = false;
				}
				else{
					r = (r+1)%2;
					back = true;
				}
			}
		case 'w':
			return true;    		
		}

		lastHit = G;
		//hitBefore = (this.opponentGrid.returnCharacter(G.get_X(), G.get_Y()) == Grid.HIT);
		//if (hitBefore) lastHit=G;

		return false;
	}

	public void addFleet(){ // Creates a fleet of boats
		for(int i=2; i<=4; i++){
			boolean add = false;
			while(!add) {
				add = this.addBoat(i);				
			}
		}
	}

	public boolean addBoat(int b){
		boolean add= false;
		int x = (int) (Math.random()*4);
		int y = (int) (Math.random()*4);
		char orient;
		int orien = (int) Math.random();
		if (orien==0) orient='h';
		else orient='v';
		add  = this.createShip(b,x,y,orient);
		return add;
	}


}