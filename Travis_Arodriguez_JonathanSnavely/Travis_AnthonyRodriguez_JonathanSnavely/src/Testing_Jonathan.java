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
	
	//Out of date: Re-implement if necessary
	/*@Test
	public void testRoomStateEnum() {

		String result = "";
		for(RoomState room: RoomState.values()){
			result += room;
		}
		assertEquals(result, "HUNTERHIDDENROOMSLIMEBLOODGOOPWUMPUSPITVISITED");
	}
	
	@Test
	public void testRoomStateEnumValues() {

		String result = "";
		for(RoomState room: RoomState.values()){
			result += room.getValue();
		}
		assertEquals(result, "OXSBGWP ");
	}*/		
	
	@Test
	public void testGridToString() {
		Grid grid = new Grid(true);
		String expected = "";
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(j == 0 || j == 3 || j == 6 || j == 9)
					expected += RoomState.BLOOD.getValue() + " ";
				else if(j == 1 || j == 4 || j == 7)
					expected += RoomState.GOOP.getValue() + " ";	
				else
					expected += RoomState.PIT.getValue() + " ";
			}	
			expected = expected.trim() + "\n";
		}
		assertEquals(expected, grid.toString());
	}			
	
	@Test
	public void testGridPrintout(){
		Grid grid = new Grid();
		grid.RevealRooms();
		System.out.println(grid);
	}
}
