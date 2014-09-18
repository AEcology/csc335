package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.GameStatus;
import model.Grid;

public class TextViewPanel extends JPanel implements Observer{
	//If we want this to be a separate class, we are going to need to maintain
	//a copy of the grid that is updated whenever we get a message
	Grid game;
	GameStatus status;

	//Need to initialize with a reference to game or crash will
	//occur upon first paint()
	public TextViewPanel(Grid initGame){
		super();
		setBackground(Color.BLACK);
		game = initGame;
		this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
	}
	
	@Override
	public void update(Observable arg0, Object roomState) {
		status = (GameStatus)roomState;
		game = (Grid) arg0;
			
		if(status == GameStatus.SHOTMISSED)
			game.RevealRooms();

		else if(status == GameStatus.SHOTHIT)
			game.RevealRooms();

		else if(status == GameStatus.DIEDWUMPUS)
			game.RevealRooms();
		
		else if(status == GameStatus.DIEDPIT)
			game.RevealRooms();
		/*
		else if(status == GameStatus.ONSLIME)
			game.RevealRooms();

		else if(status == GameStatus.ONBLOOD)
			game.RevealRooms();

		else if(status == GameStatus.ONMUD)
			game.RevealRooms();
		*/
		
		//TODO: End the game if appropriate here

		repaint();
	}
	
	//TODO: Implement the display on the JPanel
	@Override
	protected void paintComponent(Graphics g){
		setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		super.paintComponent(g);
		for(int i=0; i<10; ++i)
			g.drawString(game.toString().substring(i*40,i*40+40), 17, 40*(i+1)+20);
		if (status==GameStatus.SHOTHIT)
			g.drawString("You got 'em! YAAAY!", 120, 480);
		else if (status==GameStatus.SHOTMISSED)
			g.drawString("I'm gonna die down here...", 120, 480);
		else
			g.drawString(game.getRoomText(), 120, 480);
	}
}