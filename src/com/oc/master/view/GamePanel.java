package com.oc.master.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.oc.master.controller.Controller;
import com.oc.master.model.observer.Observee;
import com.oc.master.model.observer.Observer;

/**
 * Game panel where we actually play with the computer 
 * Any type of game involved !!
 * @author bob
 *
 */
public class GamePanel extends MainContainer implements Observer {
	
	private Controller controller;
	
	/**
	 * Constructor for the HomePanel class
	 * @param dim
	 */
	public GamePanel(Dimension dim, Observee mod){
		super(dim);
		this.controller = new Controller(mod);
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void home() {
		// TODO Auto-generated method stub
		
	}
}
