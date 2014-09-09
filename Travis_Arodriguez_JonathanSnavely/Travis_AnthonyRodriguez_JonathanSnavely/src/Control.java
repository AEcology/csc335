import java.util.Scanner;


public class Control {

	public static void main(String[] args) {
		System.out.println("Hunt the Wumpus!");
		PrintControls();
		String response = " ";
		Scanner in = new Scanner(System.in);

		while(true){	
			response = in.nextLine();
			if (response.equals("q") || response.equals("Q"))
				break;
			if(response.equals("n") || response.equals("N")){
				RunGame();
				break;
			}
		}
		
		in.close();
	}
	
	public static void PrintControls(){
		System.out.println();
		System.out.println("Controls:");
		System.out.println("Up: W");
		System.out.println("Down: S");
		System.out.println("Left: A");
		System.out.println("Right: D");
		System.out.println("Shoot: K");
		System.out.println();
		System.out.println("New game: N");
		System.out.println("Quit: Q");
	}
	
	public static boolean RunGame(){
		boolean keepGoing = true;
		Grid game = new Grid();
		Scanner input = new Scanner(System.in);
		String command = "n";
		RoomState currRoom = RoomState.EMPTY;
		System.out.println(game);
		PrintState(currRoom);
		while(keepGoing){
			command = input.nextLine();
			if (command.equals("Q") || command.equals("q")){
				System.out.println("Pretty scary, huh?");
				break;
			}
			else if (command.equals("N") || command.equals("n")){
				System.out.println("What!? You still have to hunt the wumpus!");
				break;
			}
			else if (command.equals("K") || command.equals("k")){
				while(!(command.equals("L") || command.equals("l") || command.equals("U") || command.equals("u") || command.equals("R") ||
						command.equals("r") || command.equals("D") || command.equals("d") ) ){
					System.out.println("Shoot which direction?");
					System.out.println("L, R, U, D?");
					command = input.nextLine();
				}
				boolean success = true;
				//With wraparound, difference between left/right and up/down doesnt matter
				if ( command.equals("L") || command.equals("l") || command.equals("R") || command.equals("r") )
					success = game.Shoot(Direction.LEFT);
				else if ( command.equals("U") || command.equals("u") || command.equals("D") || command.equals("d") )
					success = game.Shoot(Direction.UP);
				keepGoing = false;
				game.RevealRooms();
				System.out.println(game);
				if (success)
					System.out.println("Wumpus down! You are a hero! Yaaaaay!");
				else
					System.out.println("You missed. Probably should have brought more arrows.");
			}
			else if (command.equals("W") || command.equals("w")){
				currRoom = game.Move(Direction.UP);
			}
			else if (command.equals("S") || command.equals("s")){
				currRoom = game.Move(Direction.DOWN);
			}
			else if (command.equals("A") || command.equals("a")){
				currRoom = game.Move(Direction.LEFT);
			}
			else if (command.equals("D") || command.equals("d")){
				currRoom = game.Move(Direction.RIGHT);
			}
		//	else
		//		PrintControls();
			if (currRoom==RoomState.WUMPUS || currRoom==RoomState.PIT){
				keepGoing = false;
				game.RevealRooms();
				System.out.println(game);
				PrintState(currRoom);
			}
			else if (keepGoing){
				System.out.println(game);
				PrintState(currRoom);
			}
		}
	
		input.close();
		return false;
	}
	
	public static void PrintState(RoomState state){
		if (state==RoomState.EMPTY)
			System.out.println("It's quiet......Too quiet >_> ..... <_<");
		else if (state==RoomState.BLOOD)
			System.out.println("There's totally blood on the floor...");
		else if (state==RoomState.GOOP)
			System.out.println("There's some mixture of blood and slime here...");
		else if (state==RoomState.SLIME)
			System.out.println("There's slime on the ground here...");
		else if (state==RoomState.WUMPUS)
			System.out.println("You found the wumpus! But..you won't live to tell about it :(");
		else if (state==RoomState.PIT)
			System.out.println("You fell down a pit, dummy!");
		else
			System.out.println("If you're seeing this, it means we are terrible programmers");
	}

}
