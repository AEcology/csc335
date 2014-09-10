/**
 * JUnit testing
 * 
 * @author Anthony Rodriguez
 * @author Jonathan Snavely
 *
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class Testing_Jonathan {

	@Test
	public void testDirectionEnum() {

		String result = "";
		for(Direction direction : Direction.values()){
			result += direction;
		}
		
		assertEquals(result, "UPDOWNLEFTRIGHT");
	}
	
	@Test
	public void testRoomStateEnum() {

		String result = "";
		for(RoomState room: RoomState.values()){
			result += room;
		}
		assertEquals(result, "HUNTEREMPTYSLIMEBLOODGOOPWUMPUSPITHIDDENVISITED");
	}
	
	@Test
	public void testRoomStateEnumValues() {

		String result = "";
		for(RoomState room: RoomState.values()){
			result += room.getValue();
		}
		assertEquals(result, "[O][ ][S][B][G][W][P][X][ ]");
	}				
	
	@Test
	public void testSlimePit(){
		Grid grid = new Grid(true);
		RoomState currRoom;
		currRoom = grid.Move(Direction.UP);
		assertEquals(currRoom, RoomState.EMPTY);
		currRoom = grid.Move(Direction.RIGHT);
		assertEquals(currRoom, RoomState.SLIME);
		currRoom = grid.Move(Direction.RIGHT);
		assertEquals(currRoom, RoomState.PIT);
	}	
	
	@Test
	public void testArrow(){
		Grid grid = new Grid(true);
		assertEquals(grid.Shoot(Direction.RIGHT), true);
		assertEquals(grid.Shoot(Direction.LEFT), true);
		assertEquals(grid.Shoot(Direction.UP), false);
		assertEquals(grid.Shoot(Direction.DOWN), false);
	}		
		
	@Test
	public void testGoop(){
		Grid grid = new Grid(true);
		assertEquals(grid.Move(Direction.DOWN), RoomState.EMPTY);	
		assertEquals(grid.Move(Direction.RIGHT), RoomState.GOOP);
	}
	
//	@Test
//	public void testGridPrintout(){
//		Grid grid = new Grid(true);
//		grid.RevealRooms();
//		System.out.println(grid);
//	}
}
