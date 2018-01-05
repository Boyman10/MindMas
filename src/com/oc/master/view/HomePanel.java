package com.oc.master.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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
	 * Initialisation of the panel
	 */
	public void initPanel(){
		
		JLabel title = new JLabel("Welcome here\n");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(comics30);
		this.panel.add(title, BorderLayout.NORTH);
		
		this.panel.add(new JLabel(new ImageIcon("images/accueil.jpg")), BorderLayout.CENTER);
		
		JTextArea txt = new JTextArea(	"Welcome here man !\n" +
											"Please pick the game you wish to play !\n");
		txt.setFont(arial);
		txt.setEditable(false);
		txt.setBackground(Color.white);
		
		this.panel.add(txt, BorderLayout.SOUTH);
	}
}
