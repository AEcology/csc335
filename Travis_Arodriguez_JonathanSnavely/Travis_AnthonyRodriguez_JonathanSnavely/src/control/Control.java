package control;

import java.util.Scanner;

import view.*;
import model.*;

/**
 * Class simply initiates the game
 * @author Anthony Rodriguez
 * @author Jonathan Snavely
 */
public class Control {

	public static void main(String[] args) {
		System.out.println("Hunt the Wumpus!");
		//PrintControls();
		new WumpusGUI().setVisible(true);
	}
	
	//Prints controls for game
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
}