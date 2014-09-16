package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import model.Grid;

public class WumpusGUI extends JFrame{

	private Grid game;
	
	//Click for graphical view
	JToggleButton GUIButton;
	//Click for text view
	JToggleButton TextViewButton;
	//Text view
	TextViewFrame TextBox;
	
	
	public WumpusGUI(){
		setTitle("Hunt the Wumpus!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocation(200, 100);
		setLayout(null);
		GUIButton = new JToggleButton("Graphical View");
		GUIButton.addActionListener(new GUIButtonListener());
		GUIButton.setSize(150, 20);
		GUIButton.setLocation(250,20);
		add(GUIButton);
		TextViewButton = new JToggleButton("Text View");
		TextViewButton.setSize(150, 20);
		TextViewButton.setLocation(400,20);
		TextViewButton.addActionListener(new TextButtonListener());
		add(TextViewButton);
		TextViewButton.setSelected(false);
		GUIButton.setSelected(true);
	}
	
	private class TextViewFrame extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawString(game.toString(), 0, 0);
		}
	}
	
	private class GUIViewFrame extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			//TODO Paint images according to grid state
		}
	}
	
	private class TextButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TextViewButton.setSelected(true);
			GUIButton.setSelected(false);
			//TODO: switch to text view
		}
	}
	
	private class GUIButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			TextViewButton.setSelected(false);
			GUIButton.setSelected(true);
			// TODO Switch to GUI view
		}	
	}
	
}
