/**
 * An enumerated type containing...
 * 
 * @author Anthony Rodriguez
 * @author Jonathan Snavely
 *
 */
public enum RoomState {
	HUNTER 		("O"),
	HIDDENROOM 	("X"),
	SLIME 		("S"),
	BLOOD		("B"),
	GOOP		("G"),
	WUMPUS		("W"),
	PIT			("P"),
	VISITED		(" ");
	
    private final String roomValue;
    
    RoomState(String roomValue){
    	this.roomValue = roomValue;
    }
    
    //Use for toString() of Grid
    public String getValue(){
    	return this.roomValue;
    }
}
