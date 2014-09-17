package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;

import model.Grid;

public class WumpusGUI extends JFrame{

	private Grid game;
	private TextViewPanel textViewPanel;
	private GUIViewPanel guiViewPanel;
	private JTabbedPane viewContainer;
	
	private MoveButtonPanel moveButtonPanel;
	private ArrowButtonPanel arrowButtonPanel;
	
	public WumpusGUI(){
		textViewPanel = new TextViewPanel();
		guiViewPanel = new GUIViewPanel();
		viewContainer = new JTabbedPane();
		moveButtonPanel = new MoveButtonPanel();
		arrowButtonPanel = new ArrowButtonPanel();
		
		layoutThisFrame();
		addListeners();	//TODO: will not need, listeners implemented within their container JPanels
			
//		GUIButton = new JToggleButton("Graphical View");
//		GUIButton.addActionListener(new GUIButtonListener());
//		GUIButton.setSize(150, 20);
//		GUIButton.setLocation(250,20);
//		add(GUIButton);
//		TextViewButton = new JToggleButton("Text View");
//		TextViewButton.setSize(150, 20);
//		TextViewButton.setLocation(400,20);
//		TextViewButton.addActionListener(new TextButtonListener());
//		add(TextViewButton);
//		TextViewButton.setSelected(false);
//		GUIButton.setSelected(true);
	}
	
	private void layoutThisFrame(){
		setTitle("Hunt the Wumpus!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocation(200, 100);
		setLayout(null);		
		
		viewContainer.setSize(400, 400);
		viewContainer.setLocation(200, 50);
		viewContainer.addTab("Graphical View", guiViewPanel);
		viewContainer.addTab("Text View", textViewPanel);
		add(viewContainer);
		
		guiViewPanel.setBackground(Color.BLUE);
		textViewPanel.setBackground(Color.RED);
		
		moveButtonPanel.setSize(100, 100);
		moveButtonPanel.setLocation(50, 100);
		arrowButtonPanel.setSize(100, 100);
		arrowButtonPanel.setLocation(50, 300);		
		
		add(moveButtonPanel);
		add(arrowButtonPanel);
	}
	
	private void addListeners(){
		
	}
	
	
	private class TextViewPanel extends JPanel{
//		@Override
//		protected void paintComponent(Graphics g){
//			g.setColor(Color.BLUE);
//			super.paintComponent(g);
//			//g.drawString(game.toString(), 0, 0);
//		}
	}
	
	private class GUIViewPanel extends JPanel{
//		@Override
//		protected void paintComponent(Graphics g){
//			g.setColor(Color.RED);
//			super.paintComponent(g);
//			//TODO Paint images according to grid state
//		}
	}
}
