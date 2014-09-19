package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import model.Direction;
import model.GameStatus;
import model.Grid;

public class WumpusGUI extends JFrame implements Observer{

	private Grid game;
	private TextViewPanel textViewPanel;
	private GUIViewPanel guiViewPanel;
	private JTabbedPane viewContainer;
	
	private MoveButtonPanel moveButtonPanel;
	private ArrowButtonPanel arrowButtonPanel;
	
	public WumpusGUI(){
		game = new Grid();
		textViewPanel = new TextViewPanel(game);
		guiViewPanel = new GUIViewPanel(game);
		viewContainer = new JTabbedPane();
		moveButtonPanel = new MoveButtonPanel();
		arrowButtonPanel = new ArrowButtonPanel();
		
		layoutThisFrame();
		
		game.addObserver((Observer) textViewPanel);
		game.addObserver((Observer) guiViewPanel);
		game.addObserver(this);
	}
	
	private void layoutThisFrame(){
		/*JFrame Editing*/
		setTitle("Hunt the Wumpus!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocation(200, 100);
		setLayout(null);		
		
		/*JTabbedPane editing*/
		viewContainer.setSize(550, 550);
		viewContainer.setLocation(200, 0);
		viewContainer.addTab("Graphical View", guiViewPanel);
		viewContainer.addTab("Text View", textViewPanel);
		add(viewContainer);
		
		/*JTabbedPane JPanel editing*/
		guiViewPanel.setBackground(Color.BLUE);
		guiViewPanel.repaint();
		textViewPanel.setBackground(Color.RED);
		textViewPanel.repaint();
		
		/*Button Panel editing*/
		moveButtonPanel.setSize(100, 100);
		moveButtonPanel.setLocation(50, 100);
		arrowButtonPanel.setSize(100, 100);
		arrowButtonPanel.setLocation(50, 300);		
		add(moveButtonPanel);
		add(arrowButtonPanel);
	}
	
	/*JPanel of arrow buttons. Contains inner class listeners*/
	private class ArrowButtonPanel extends JPanel{
		private BasicArrowButton up, down, left, right;

		private class ButtonMoveListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == up)
					game.Shoot(Direction.UP);
				else if (arg0.getSource() == down)
					game.Shoot(Direction.DOWN);
				else if (arg0.getSource() == left)
					game.Shoot(Direction.LEFT);
				else
					game.Shoot(Direction.RIGHT);	
			}
		}
		
		private ArrowButtonPanel() {
			super();
			ActionListener buttonListener = new ButtonMoveListener();
			this.setLayout(new GridLayout(2, 3)); // grid layout 
			this.setPreferredSize(new Dimension(75, 50)); // set size
			this.setBackground(Color.white);
			
			/* Create the arrow buttons */
			up = new BasicArrowButton(SwingConstants.NORTH);
			down = new BasicArrowButton(SwingConstants.SOUTH);
			left = new BasicArrowButton(SwingConstants.WEST);
			right = new BasicArrowButton(SwingConstants.EAST);
			
			/* Ignore this part, swing is really stupid sometimes */
			up.setFocusable(false);
			down.setFocusable(false);
			left.setFocusable(false);
			right.setFocusable(false);
			
			/* Add the action listener to each button */
			up.addActionListener(buttonListener);
			down.addActionListener(buttonListener);
			left.addActionListener(buttonListener);
			right.addActionListener(buttonListener);
			
			/* Add each button (and empty panels) */
			add(new JPanel());
			add(up);
			add(new JPanel());
			add(left);
			add(down);
			add(right);
		}
	}
	
	private class MoveButtonPanel extends JPanel{

		private BasicArrowButton up, down, left, right;
		
		private class ButtonMoveListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == up)
					game.Move(Direction.UP);
				else if (arg0.getSource() == down)
					game.Move(Direction.DOWN);
				else if (arg0.getSource() == left)
					game.Move(Direction.LEFT);
				else
					game.Move(Direction.RIGHT);	
			}
		}	
		
		private MoveButtonPanel() {
			super();
			ActionListener buttonListener = new ButtonMoveListener();
			this.setLayout(new GridLayout(2, 3)); // grid layout 
			this.setPreferredSize(new Dimension(75, 50)); // set size
			this.setBackground(Color.white);
			
			/* Create the arrow buttons */
			up = new BasicArrowButton(SwingConstants.NORTH);
			down = new BasicArrowButton(SwingConstants.SOUTH);
			left = new BasicArrowButton(SwingConstants.WEST);
			right = new BasicArrowButton(SwingConstants.EAST);
			
			/* Ignore this part, swing is really stupid sometimes */
			up.setFocusable(false);
			down.setFocusable(false);
			left.setFocusable(false);
			right.setFocusable(false);
			
			/* Add the action listener to each button */
			up.addActionListener(buttonListener);
			down.addActionListener(buttonListener);
			left.addActionListener(buttonListener);
			right.addActionListener(buttonListener);
			
			/* Add each button (and empty panels) */
			add(new JPanel());
			add(up);
			add(new JPanel());
			add(left);
			add(down);
			add(right);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		GameStatus status = (GameStatus)arg1;
		if(status == GameStatus.SHOTMISSED){
			remove(arrowButtonPanel);
			remove(moveButtonPanel);
		}
		else if(status == GameStatus.SHOTHIT){
			remove(arrowButtonPanel);
			remove(moveButtonPanel);
		}
		else if(status == GameStatus.DIEDWUMPUS){
			remove(arrowButtonPanel);
			remove(moveButtonPanel);
		}
		else if(status == GameStatus.DIEDPIT){
			remove(arrowButtonPanel);
			remove(moveButtonPanel);
		}
	}
}
