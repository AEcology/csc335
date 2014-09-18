package model;

/**
 * Contains the status of the game. This object is sent to the Observer's update methods to decrease instructions needed.
 * 
 * @author Anthony Rodriguez
 * @author Jonathan Snavely
 */
public enum GameStatus {
	SHOTMISSED,
	SHOTHIT,
	DIEDWUMPUS,
	DIEDPIT,
	ONSLIME,
	ONBLOOD,
	ONMUD,
	ONNOTHING;
}
