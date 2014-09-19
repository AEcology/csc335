package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.GameStatus;
import model.Grid;
import model.RoomState;


public class GUIViewPanel extends JPanel implements Observer{
	Grid game;
	int dx;
	int dy;
	Image floor;
	Image blood;
	Image goop;
	Image slime;
	Image pit;
	Image hunter;
	Image wumpus;
	
	public GUIViewPanel(Grid initGame){
		super();
		game = initGame;
		dx = 50;
		dy = 50;
		try {
			floor = ImageIO.read(getClass().getResource("/WumpusImages/Ground.png"));
			blood = ImageIO.read(getClass().getResource("/WumpusImages/Blood.png"));
			goop = ImageIO.read(getClass().getResource("/WumpusImages/Goop.png"));
			slime = ImageIO.read(getClass().getResource("/WumpusImages/Slime.png"));
			pit = ImageIO.read(getClass().getResource("/WumpusImages/SlimePit.png"));
			hunter = ImageIO.read(getClass().getResource("/WumpusImages/TheHunter.png"));
			wumpus = ImageIO.read(getClass().getResource("/WumpusImages/Wumpus.png"));
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(Color.BLACK);
		repaint();
	}
	
	
	@Override
	public void update(Observable arg0, Object roomState) {
		GameStatus status = (GameStatus)roomState;
		game = (Grid) arg0;
			
		if(status == GameStatus.SHOTMISSED)
			game.RevealRooms();

		else if(status == GameStatus.SHOTHIT)
			game.RevealRooms();

		else if(status == GameStatus.DIEDWUMPUS)
			game.RevealRooms();
		
		else if(status == GameStatus.DIEDPIT)
			game.RevealRooms();

		repaint();
	}
	
	private void PaintTiles(Graphics g){
		for (int i=0; i<10; ++i)
			for (int j=0; j<10; ++j){
				if (game.getCurrCol()==i && game.getCurrRow()==j){
					g.drawImage(floor, i*dx+20, j*dy+10, null);
					//draw roomstate
					if (game.getCurrentRoom()==RoomState.SLIME)
						g.drawImage(slime, i*dx+20, j*dy+10, null);
					else if (game.getCurrentRoom()==RoomState.PIT)
						g.drawImage(pit, i*dx+20, j*dy+10, null);
					else if (game.getCurrentRoom()==RoomState.WUMPUS)
						g.drawImage(wumpus, i*dx+20, j*dy+10, null);
					else if (game.getCurrentRoom()==RoomState.BLOOD)
						g.drawImage(blood, i*dx+20, j*dy+10, null);
					else if (game.getCurrentRoom()==RoomState.PIT)
						g.drawImage(pit, i*dx+20, j*dy+10, null);
					else if (game.getCurrentRoom()==RoomState.GOOP)
						g.drawImage(goop, i*dx+20, j*dy+10, null);
					g.drawImage(hunter, i*dx+20, j*dy+10, null);
				}
				else if (game.isVisible(j, i)){
					g.drawImage(floor, i*dx+20, j*dy+10, null);
					if (game.state(j, i)==RoomState.SLIME)
						g.drawImage(slime, i*dx+20, j*dy+10, null);
					else if (game.state(j, i)==RoomState.PIT)
						g.drawImage(pit, i*dx+20, j*dy+10, null);
					else if (game.state(j, i)==RoomState.WUMPUS)
						g.drawImage(wumpus, i*dx+20, j*dy+10, null);
					else if (game.state(j, i)==RoomState.BLOOD)
						g.drawImage(blood, i*dx+20, j*dy+10, null);
					else if (game.state(j, i)==RoomState.PIT)
						g.drawImage(pit, i*dx+20, j*dy+10, null);
					else if (game.state(j, i)==RoomState.GOOP)
						g.drawImage(goop, i*dx+20, j*dy+10, null);
				}
				//else do nothing, tile is already black	
			}
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(Color.BLACK);
		PaintTiles(g);
	}
}