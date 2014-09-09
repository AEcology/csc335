//TODO: Delete this class (Too lazy to figure out how do delete from repo yet)


public class Room {
	/*
	 * Note From Jonathan:
	 * I think this class may be completely unnecessary for our purposes. If you consider the grid room assignment process, we could simply do 
	 * 
	 * for(each room){
	 * 	room = random RoomState
	 * }
	 * 
	 * Additionally this would simplify any grid searching algorithm to simply check for state like the following:
	 * 
	 * if(room == RoomState.Blood)
	 * 	do action;
	 * 
	 * if(room == RoomState.Wumpus)
	 * 	you are dead;
	 * 
	 * etc, where room is a RoomState enum value...
	 * 
	 * But, if we used the room class it serves as an unnecessary middleman...
	 * 	if(room.getState() == RoomState.Blood)
	 * 	do action;
	 * 
	 * if(room.getState() == RoomState.Wumpus)
	 * 	you are dead;
	 * 
	 * etc, where room is a Room object. 
	 * 
	 */
}
