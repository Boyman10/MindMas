package com.oc.master.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Home panel where sit different details and the choice of game mode
 * using a radio box
 * @author bob
 */
public class HomePanel extends MainContainer {
	
	/**
	 * Constructor for the HomePanel class
	 * @param dim
	 */
	public HomePanel(Dimension dim){
		super(dim);
		initPanel();
	}

	/**
	 * Initialization of the panel
	 */
	public void initPanel(){
				
	    LayoutManager layout = new BoxLayout(this.panel, BoxLayout.Y_AXIS);
	    this.panel.setLayout(layout);
	    
		JLabel title = new JLabel("Welcome here");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(comics30);

		ImageIcon imgIcon = new ImageIcon("res/images/mastermind.jpg");
		JLabel lblIcon = new JLabel(imgIcon);

		JLabel txt = new JLabel(	"<html><center><p>Welcome here man !</p>" +
											"<p>Please pick the game you wish to play !</p></center></html>");
		txt.setFont(arial);
		txt.setBackground(Color.white);
		
		JButton gameButton = new JButton("New Game");
		gameButton.setActionCommand("game");
		gameButton.setBackground(new Color(0x2dce98));
		gameButton.setForeground(Color.white);
		
	    title.setAlignmentX(Component.CENTER_ALIGNMENT);
	    lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
	    txt.setAlignmentX(Component.CENTER_ALIGNMENT);
	    gameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
		this.panel.add(title);
		this.panel.add(lblIcon);		
		this.panel.add(txt);
		this.panel.add(gameButton);
	}
}
