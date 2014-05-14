package uk.ac.gcu.battleships;

	public class Ship {
		int shipSize;				//Size of the ship 2, 3 or 4
		int shipPosition_x;			//Ship start position on board; x, y
		int shipPosition_y;
		char[] shipHealth;			//Ship health. bow, rear, stern
		int[][] shipCoordinates;		//Every coordinate in which the ship exists. X,Y
		char shipOrientation;			//Input orientation on creation Note to self: catch exceptions
		
		//Used by crew to ponder if their ship has been hit.
		boolean testHit(int x_guess, int y_guess){
			for(byte i = 0; i < this.shipSize; i++){
				if(this.shipCoordinates[i][0] == x_guess && this.shipCoordinates[i][1] == y_guess){
					this.markHit(i);
					return true;
				}
			}
			return false;
		}

		//Useless now. Leaving for testing purposes
		//Used to determine if the position n of the ship has already been hit
		public boolean isHit(int position){
			if(this.shipHealth[position] == '+')
				return false;
			else
				return true;
		}
		
		//Used to mark the ships shipHealth array whenever the ship takes a hit. Called by testHit
		void markHit(int position){
			this.shipHealth[position] = 'o';
		}
		
		//Tells whether the ship has been sunk
		boolean isSunk(){
			//The ship has been sunk if no '+' is found in shipHealth
			for(byte i = 0; i < this.shipSize; i++){
				if(this.shipHealth[i] == '+')
					return true;
			}
			return false;
		}
		
		void resetShipHealth(){
			for(byte i = 0; i < this.shipHealth.length; i++){
				this.shipHealth[i] = '+';
			}
		}
		
		/*With this, on creation, the ship will know every place it occupies on the grid.*/
		void calculateShipPosition(){
			this.shipCoordinates[0][0] = this.shipPosition_x;
			this.shipCoordinates[0][1] = this.shipPosition_y;
			
			/*For vertical ships*/
			if(this.shipOrientation == 'v' || this.shipOrientation == 'V'){
				for(byte x = 1; x < this.shipSize; x++){
					this.shipCoordinates[x][0] = this.shipPosition_x;
					this.shipCoordinates[x][1] = this.shipCoordinates[x-1][1] + 1;
				}
			/*For horizontal ships*/
			} else {
				for(byte x = 1; x < this.shipSize; x++){
					this.shipCoordinates[x][0] = this.shipCoordinates[x-1][0] + 1;
					this.shipCoordinates[x][1] = this.shipPosition_y;
				}
			}
		}
		
		/*	Ship constructor */
		public Ship(int size, int position_x, int position_y, char orientation){
			
			this.shipHealth = new char[size];
			this.shipCoordinates = new int[size][2];
			this.shipSize = size;
			this.shipPosition_x = position_x;
			this.shipPosition_y = position_y;
			this.shipOrientation = orientation;
			resetShipHealth();
			calculateShipPosition();
		}
}
