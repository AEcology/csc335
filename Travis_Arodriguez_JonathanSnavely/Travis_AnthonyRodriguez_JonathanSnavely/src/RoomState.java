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
	PIT			("P");
	
    private final String roomValue;
    private boolean hidden;
    
    RoomState(String roomValue){
    	this.roomValue = roomValue;
    	this.hidden = true;
    }
    
    public void visit(){
    	this.hidden = false;
    }
    
    public boolean isHidden(){
    	return this.hidden;
    }
    
    //Use for toString() of Grid
    public String getValue(){
    	if (hidden)
    		return "[X]";
    	else
    		return "[" + this.roomValue + "]";
    }
}
