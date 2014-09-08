import java.util.Random;

/**
 * This class describes a 10x10 grid object. Houses information regarding the state of each room on the game board, as well as external interfaces for a player
 * to change the contents of the grid.
 * 
 * @author Anthony Rodriguez
 * @author Jonathan Snavely
 *
 */
public class Grid {

	RoomState grid[][];
	
	public Grid(){
		grid = new RoomState[10][10];

		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++)
				grid[i][j] = RoomState.HIDDENROOM;
		}
		
		placePitsAndSlime();
		placeWumpusBloodAndMud();
		placeHunter();
	}
	
	//This is for static testing.
	public Grid(boolean value){
		
		grid = new RoomState[10][10];
		for(int i = 0; i < grid.length; i++){

			for(int j = 0; j < grid.length; j++){
				if(j == 0 || j == 3 || j == 6 || j == 9)
					grid[i][j] = RoomState.BLOOD;
				else if(j == 1 || j == 4 || j == 7)
					grid[i][j] = RoomState.GOOP;	
				else
					grid[i][j] = RoomState.PIT;
			}
		}
	}
	
	
	/**
	 * Adds 3-5 pits and associated slime.
	 */
	private void placePitsAndSlime(){
		Random rand = new Random();
		int pitCount = rand.nextInt(3) + 3;
		int x, y;
		
		//Assign Pits
		for(int i = 0; i < pitCount; i++){
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			
			if(grid[x][y] != RoomState.HIDDENROOM)
				i--;
			else
				grid[x][y] = RoomState.PIT;
		}
		
	    //Assign Slime
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				
				if(grid[i][j] == RoomState.PIT){
					assignSlime(i-2, j);
					assignSlime(i+2, j);
					assignSlime(i, j+2);
					assignSlime(i, j-2);
				}
			}
		}
	}
		
	//Helper method. Feed x,y coordinates. Will fill in appropriate slime indication "S". Cannot overwrite a pit "P"
	private void assignSlime(int i, int j) {
		//ToDo
		
		//Explicit Checks
		if(i == 0 && j == 0){
			
		}
		else if(i == 1 && j == 0){
			
		}
		else if(i == 0 && j == 1){
		
		}
		else if(i == 1 && j == 1){
			
		}
		
		//....
		
	}



	private void placeWumpusBloodAndMud(){
		//ToDo
	}
	private void placeHunter(){
		//ToDo
	}
	public RoomState Move(Direction d){
		//ToDo
		return RoomState.BLOOD;
	}
	public boolean Shoot(Direction d){
		//ToDo
		return true;
	}
	
	public String toString(){
		
		String result = "";
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid.length; j++)
				result += grid[i][j].getValue() + " ";
			result = result.trim() + "\n";
		}
		
		return result;		
	}
}
