package model;
/**
 * An enumerated type containing...
 * 
 * @author Anthony Rodriguez
 * @author Jonathan Snavely
 *
 */
public enum RoomState {
	HUNTER 		("O"),
	EMPTY	 	(" "),
	SLIME 		("S"),
	BLOOD		("B"),
	GOOP		("G"),
	WUMPUS		("W"),
	PIT			("P"),
	HIDDEN		("X"),
	VISITED		(" ");
	
    private final String roomValue;
    
    RoomState(String roomValue){
    	this.roomValue = roomValue;
    }
      
    public String getValue(){
    	return "[" + this.roomValue + "]";
    }
}
