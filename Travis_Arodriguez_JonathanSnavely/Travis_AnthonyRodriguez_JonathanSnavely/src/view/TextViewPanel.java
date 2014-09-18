package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.GameStatus;
import model.Grid;

public class TextViewPanel extends JPanel implements Observer{

	public TextViewPanel(){
		super();
		setBackground(Color.RED);
	}
	
	@Override
	public void update(Observable arg0, Object roomState) {
		GameStatus status = (GameStatus)roomState;
		Grid game = (Grid) arg0;
			
		if(status == GameStatus.SHOTMISSED)
			game.RevealRooms();

		else if(status == GameStatus.SHOTHIT)
			game.RevealRooms();

		else if(status == GameStatus.DIEDWUMPUS)
			game.RevealRooms();
		
		else if(status == GameStatus.DIEDPIT)
			game.RevealRooms();
		
		else if(status == GameStatus.ONSLIME)
			game.RevealRooms();

		else if(status == GameStatus.ONBLOOD)
			game.RevealRooms();

		else if(status == GameStatus.ONMUD)
			game.RevealRooms();
				
		
		//TODO: End the game if appropriate here

		repaint();
	}
	
	//TODO: Implement the display on the JPanel
//	@Override
//	protected void paintComponent(Graphics g){
////		g.setColor(Color.BLUE);
////		super.paintComponent(g);
//		g.drawString("hello", 0, 0);
//	}
}