package uk.ac.gcu.battleships;

public class Player  {
    public String name;
    public Grid myGrid;
    public Grid opponetGrid;
     
    public Player(){
        myGrid = new Grid(10);
    }
     
 
    public boolean createShip(int size, int x, int y, char orientation){
        Ship S = new Ship(size, x, y, orientation);
        return myGrid.addShip(S);
    }
 
     
    public boolean makeGuess(Guess G){
    	return opponetGrid.checkGuess(G);
    }
      

     
    //TESTING
    /*
public static void main(String[] args) {
        Game game = new Game();
        Player p1 = new Player();   
        Guess g1 = new Guess((byte)2,(byte)2);                  
        Guess g2 = new Guess((byte)2,(byte)1);                  
        System.out.println(p1.makeGuess(g1));
        System.out.println(p1.makeGuess(g2));                   
    }
    */
}
