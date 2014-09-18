package model;

import java.util.Observable;
import java.util.Random;

/**
 * This class describes a 10x10 grid object. Houses information regarding the state of each room on the game board, as well as external interfaces for a player
 * to change the contents of the grid.
 * 
 * @author Anthony Rodriguez
 * @author Jonathan Snavely
 *
 */
public class Grid extends Observable{

	private RoomState grid[][];
	private RoomState visited[][];
	
	/*Current RoomState of what the hunter is standing in*/
	private RoomState currentRoom = RoomState.EMPTY;	
	private int currRow = 0;
	private int currCol = 0;
	
	//Constructor
	public Grid(){
		grid = new RoomState[10][10];
		visited = new RoomState[10][10];

		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				grid[i][j] = RoomState.EMPTY;
				visited[i][j] = RoomState.HIDDEN;
			}
		}
		
		placePitsAndSlime();
		placeWumpusBloodAndMud();
		placeHunter();
	}
	
	//Show contents of all rooms	
	public void RevealRooms(){		
		for(int i=0; i<grid.length; i++)
			for (int j=0; j<grid.length; j++)
				visited[i][j] = RoomState.VISITED;
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
			
			if(grid[x][y] != RoomState.EMPTY)
				i--;
			else
				grid[x][y] = RoomState.PIT;
		}
		
	    //Assign Slime
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				if(grid[i][j] == RoomState.PIT){
					assignSlime(i-1, j);
					assignSlime(i+1, j);
					assignSlime(i, j+1);
					assignSlime(i, j-1);
				}
			}
		}
	}
		
	//Helper method. Feed x,y coordinates. Will fill in appropriate slime indication "S". Cannot overwrite a pit "P"
	private void assignSlime(int i, int j) {

		//Roll Over
		if(i == -1)
			i = 9;
		else if(i == 10)
			i = 0;
		if(j == -1)
			j = 9;
		else if(j == 10)
			j = 0;
		
		//Assignment
		if(grid[i][j] != RoomState.PIT)
			grid[i][j] = RoomState.SLIME;
	}

	//Places Wumpus, blood, goop
	private void placeWumpusBloodAndMud(){
		Random rand = new Random();
		boolean success = false;
		int x = 0;
		int y = 0;
		//Place Wumpus
		while(!success){
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			if (grid[x][y]!=RoomState.PIT && grid[x][y]!=RoomState.SLIME){
				grid[x][y] = RoomState.WUMPUS;
				success = true;
			}
		}
		//Place blood (unrolled loop)
		assignBlood(x+1, y);
		assignBlood(x+2, y);
		assignBlood(x-1, y);
		assignBlood(x-2, y);
		assignBlood(x, y+1);
		assignBlood(x, y+2);
		assignBlood(x, y-1);
		assignBlood(x, y-2);
		assignBlood(x+1, y+1);
		assignBlood(x-1, y+1);
		assignBlood(x+1, y-1);
		assignBlood(x-1, y-1);
	}
	
	//Place blood
	private void assignBlood(int x, int y){
		if (x>9)
			x = x-10;
		else if (x<0)
			x = 10+x;
		if (y>9)
			y = y-10;
		else if (y<0)
			y = 10+y;
		if (grid[x][y]==RoomState.EMPTY)
			grid[x][y] = RoomState.BLOOD;
		else if (grid[x][y]==RoomState.SLIME)
			grid[x][y] = RoomState.GOOP;
	}
	
	//Random placement of hunter. Cannot be placed on top of any other object
	private void placeHunter(){
		Random rand = new Random();
		int x, y;

		while(true){
			x = rand.nextInt(10);
			y = rand.nextInt(10);	
			if(grid[x][y] != RoomState.SLIME && grid[x][y] != RoomState.PIT && grid[x][y] != RoomState.BLOOD && grid[x][y] != RoomState.WUMPUS && grid[x][y] != RoomState.GOOP){
				grid[x][y] = RoomState.HUNTER;
				visited[x][y] = RoomState.VISITED;
				currRow = x;
				currCol = y;
				break;
			}
		}
		
		//Hunter Coordinates
		currRow = x;
		currCol = y;
	}
	
	/**update x,y, restore state of old room,
	save state of new room and set state to HUNTER
	Note: Directions dont seem the same as their +x, +y offsets 
	because arrays are represented as arry[row][col]*/
	public void Move(Direction d){
		grid[currRow][currCol] = currentRoom;
		
		if (d==Direction.RIGHT){
			if (currCol==grid.length-1)
				currCol = 0;
			else
				++currCol;
		}
		else if (d==Direction.LEFT){
			if (currCol==0)
				currCol = grid.length-1;
			else
				--currCol;
		}
		else if (d==Direction.DOWN){
			if (currRow == grid.length-1)
				currRow = 0;
			else
				++currRow;
		}
		else{
			if (currRow == 0)
				currRow = grid.length-1;
			else
				--currRow;
		}
		
		/*Moving the hunter*/
		visited[currRow][currCol] = RoomState.VISITED;
		currentRoom = grid[currRow][currCol];
		grid[currRow][currCol] = RoomState.HUNTER;	
		
		/*Update the observers*/
		setChanged();
		if(currentRoom == RoomState.WUMPUS)
			this.notifyObservers(GameStatus.DIEDWUMPUS);
		else if(currentRoom == RoomState.PIT)
			this.notifyObservers(GameStatus.DIEDPIT);
		else if(currentRoom == RoomState.SLIME)
			this.notifyObservers(GameStatus.ONSLIME);
		else if(currentRoom == RoomState.BLOOD)
			this.notifyObservers(GameStatus.ONBLOOD);
		else if(currentRoom == RoomState.BLOOD)
			this.notifyObservers(GameStatus.ONMUD);
		else 
			this.notifyObservers(GameStatus.ONNOTHING);
	}
	
	public RoomState getCurrentRoom(){
		return currentRoom;
	}
	
	public String getRoomText(){
		if (currentRoom==RoomState.BLOOD)
			return "Blood!";
		else if (currentRoom==RoomState.EMPTY)
			return "Nothing in this room...";
		else if (currentRoom==RoomState.GOOP)
			return "There's goop on the ground!";
		else if (currentRoom==RoomState.PIT)
			return "You fell to your doom!";
		else if (currentRoom == RoomState.SLIME)
			return "SLIIIME!";
		else if (currentRoom==RoomState.WUMPUS)
			return "The Wumpus caught ye!";
		else return "Oh noes, an error is occured! #&*(&#!";
	}
	
	public int getCurrCol(){
		return currCol;
	}
	
	public int getCurrRow(){
		return currRow;
	}
	
	//Shoot: Note: x/y switched because array access is arry[row][col]
	public void Shoot(Direction d){
		if (d==Direction.UP || d==Direction.DOWN){
			for(int row=0; row<grid.length; ++row)
				if (grid[row][currCol]==RoomState.WUMPUS){
					setChanged();
					this.notifyObservers(GameStatus.SHOTHIT);
				}
					
		}
		else if (d==Direction.RIGHT || d==Direction.LEFT){
			for(int col=0; col<grid.length; ++col)
				if (grid[currRow][col]==RoomState.WUMPUS){
					setChanged();
					this.notifyObservers(GameStatus.SHOTHIT);
				}
		}
		else
			this.notifyObservers(GameStatus.SHOTMISSED);
		setChanged();
	}
	
	public String toString(){
		
		String result = "";
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid.length; j++){
				if(visited[i][j] == RoomState.HIDDEN)
					result += RoomState.HIDDEN.getValue() + " ";
				else
					result += grid[i][j].getValue() + " ";
			}
				
			result = result.trim() + "\n";
		}
		
		return result;		
	}
}
