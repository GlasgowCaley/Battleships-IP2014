package uk.ac.gcu.battleships;

public class ArtificialPlayer extends Player{

	private boolean hitBefore, hitLine, hitRow, back;
	private Guess lastHit, firstHit;
	private int r,l,rl;
	char resul;


	public ArtificialPlayer(){
		super("Computer");
		myGrid = new Grid();
		hitBefore=false;
		hitLine=false;
		hitRow=false;
		back = false;
		lastHit = new Guess(-1,-1);
		firstHit = new Guess(-1,-1);
		r=(int) Math.floor(Math.random()*2);
		l=(int) Math.floor(Math.random()*2);
		rl=(int) Math.floor(Math.random()*4);		
	}


	public boolean makeGuess(Guess G){
		//System.out.println("Last hit: "+lastHit.get_Y()+" "+lastHit.get_X());
		//System.out.println("First hit: "+firstHit.get_Y()+" "+firstHit.get_X());


		do{
			if(!hitBefore){								//If it didn't hit before, it does a random guess
				G.set_X((int)(Math.random()*5));
				G.set_Y((int)(Math.random()*5));
			}

			else if(hitLine)							//If it hit before on a line, keep shooting in the same line
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
				l = (l+1)%2; 
			}
			else if(hitRow)								//If it hit before on a row, keep shooting in the same row
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
				r = (r+1)%2;
			}

			else										//If it hit before, shoot to the near positions
			{

				switch(rl){								//Made a validation of the lastHit to not go out of the grid
				case 0:
					G.set_Y(lastHit.get_Y()+1);
					G.set_X(lastHit.get_X());
					//System.out.println("ITS WORKING0.");//output for testing

					break;
				case 1:
					G.set_Y(lastHit.get_Y()-1);
					G.set_X(lastHit.get_X());
					//System.out.println("ITS WORKING2.");//output for testing

					break;
				case 2:
					G.set_X(lastHit.get_X()+1);
					G.set_Y(lastHit.get_Y());
					//System.out.println("ITS WORKING1.");//output for testing

					break;


				case 3:

					G.set_X(lastHit.get_X()-1);
					G.set_Y(lastHit.get_Y());
					//System.out.println("ITS WORKING3.");//output for testing

					break;
				}
				rl = (rl+1)%4;
			}

		}while((this.opponentGrid.returnCharacter(G.get_Y(), G.get_X()) == 'x')||(this.opponentGrid.returnCharacter(G.get_Y(), G.get_X())=='o')
				||G.get_Y()<0 ||G.get_Y()>4||G.get_X()>4|| G.get_X()<0);

		resul = opponentGrid.checkGuessAI(G);
		//System.out.println(resul);//output for testing

		switch(resul){
		case 'h':							//It has been a hit
			lastHit.set_X(G.get_X());		//put the the guess on the lastHit
			lastHit.set_Y(G.get_Y());
			if(hitBefore)					//if it hit before, check were is the hit now to put hitLine o hit row to true
			{
				if(hitLine)  
					l--;
				else if (hitRow) 
					r--;
				else if(rl==1){
					hitLine = true;
					l=1;
				}
				else if(rl==2){
					hitLine=true;
					l=0;
				}
				else if(rl==3){
					hitRow= true;
					r=1;
				}
				else{
					hitRow= true;
					r=0;
				}
			}
			else {
				hitBefore = true;
				firstHit.set_X(G.get_X());		
				firstHit.set_Y(G.get_Y());
			}
			break;

		case 's':						//It has sunk a ship. Resets all the values and start again randomly moves
			hitBefore = false;
			hitLine = false;
			hitRow = false;
			back = false;
			break;

		case 'm':						//It has been a miss
			if(hitBefore){

				lastHit.set_X(firstHit.get_X());		
				lastHit.set_Y(firstHit.get_Y());

				if (hitLine){
					if (back){
						hitBefore = false;
						hitLine = false;
						hitRow = false;
						back = false;
					}
					else{
						back = true;
					}
				}
				else if (hitRow){

					if (back){
						hitBefore = false;
						hitLine = false;
						hitRow = false;
						back = false;
					}
					else{
						back = true;
					}
				}
			}
			break;
		case 'w':
			return true;    		
		}
		/*
		System.out.println("Current guess: "+G.get_Y()+" "+G.get_X()); //output for testing
		System.out.println("Last hit: "+lastHit.get_Y()+" "+lastHit.get_X());
		System.out.println("First hit: "+firstHit.get_Y()+" "+firstHit.get_X());
		 */
		return false;
	}

	public void addFleet(){ // Creates a fleet of boats
		for(int i=0; i<3; i++){
			boolean add = false;
			while(!add) {
				Ship s = null;				
				int x = (int) (Math.random()*4);
				int y = (int) (Math.random()*4);
				char orient;
				int orien = (int) Math.random();
				if (orien==0) orient='h';
				else orient='v';
				switch(i) {
				case 0:
					s = new Destroyer(x,y,orient);
					break;
				case 1:
					s = new Submarine(x,y,orient);					
					break;
				case 2:
					s = new Battleship(x,y,orient);					
					break;
				}
				add = this.addShip(s);
				
			}  
		}
	}


}

