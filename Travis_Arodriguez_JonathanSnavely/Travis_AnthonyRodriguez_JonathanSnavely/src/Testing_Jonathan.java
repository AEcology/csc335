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

	//Recursive FullTest: Recursively visits all safe spots in a randomly generated grid and prints the result
	@Test
	public void FullTest(){
		boolean ourGrid[][] = new boolean[10][10];
		for (int i=0; i<10; ++i)
			for (int j=0; j<10; ++j)
				ourGrid[i][j] = false;
		System.out.println("Recursive search result:");
		Grid game = new Grid();
		//ourGrid[game.getCurrRow()][game.getCurrCol()] = true;
		RecursiveStep(game, Direction.UP, ourGrid);
		System.out.println(game);
		assertTrue(true);
	}
	//Helper function for FullTest
	private void RecursiveStep(Grid game, Direction d, boolean ourGrid[][]){
		if (game.getCurrentRoom()==RoomState.GOOP || game.getCurrentRoom()==RoomState.BLOOD || game.getCurrentRoom()==RoomState.SLIME
				|| ourGrid[game.getCurrRow()][game.getCurrCol()]==true)
			return;
		else{
			ourGrid[game.getCurrRow()][game.getCurrCol()] = true;
			if (d!=Direction.DOWN){
				game.Move(Direction.UP);
				RecursiveStep(game, Direction.UP, ourGrid);
				game.Move(Direction.DOWN);
			}
			if (d!=Direction.LEFT){
				game.Move(Direction.RIGHT);
				RecursiveStep(game, Direction.RIGHT, ourGrid);
				game.Move(Direction.LEFT);
			}
			if (d!=Direction.UP){
				game.Move(Direction.DOWN);
				RecursiveStep(game, Direction.DOWN, ourGrid);
				game.Move(Direction.UP);
			}
			if (d!=Direction.RIGHT){
				game.Move(Direction.LEFT);
				RecursiveStep(game, Direction.LEFT, ourGrid);
				game.Move(Direction.RIGHT);
			}
		}
	}
	
	
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
